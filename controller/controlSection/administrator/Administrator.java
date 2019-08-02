package controller.controlSection.administrator;

import controller.attackTools.Beam;
import controller.attackTools.Bomb;
import controller.bonus.Coin;
import controller.bonus.empowerment.Turbo;
import controller.controlSection.levelize.LevelManager;
import controller.enemy.alienAttack.Spike;
import controller.enemy.alienGroups.Group;
import controller.enemy.aliens.Alien;
import controller.player.playerExtentions.Player;
import controller.ship.SpaceShip;
import view.screen.ContentPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Administrator {

    Player player ;
    SpaceShip ship ;

    LevelManager levelManager ;

    Timer timer ;

    ContentPane contentPane ;


    public Administrator(ContentPane contentPane) {
        this.contentPane = contentPane ;
        //    initialize();
    }


    protected void initialize(){
        prepareLevelManager() ;
        prepareShip();
        prepareTimer();
        timer.start();
    }

    void prepareLevelManager(){
        levelManager = new LevelManager(this) ;
    }


    public void prepareShip() {
        ship = player.getShip();
    }


    public void prepareTimer() {
        timer = new Timer(20,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    Group group = levelManager.getCurrentGroup();
                    if (group.isDead())
                        levelManager.nextGroup();
                    group.moveStuffs();
                    group.produceSpike();
                    detectCollisions();
                    bombExplosion();

            }

        });
    }


    public void finishGame(){
        contentPane.showWinPanel(player.getCoin());
        timer.stop();
    }

    protected void bombExplosion() {
        for(Bomb bomb : ship.getBombs()) {
            if(bomb.isExploded()){
                Group group = levelManager.getCurrentGroup() ;
                group.killTheGroup();
                bomb.setExplode(false);
            }
        }
    }


    public void detectCollisions() {
        beamAlienCollision();
        spaceShipAlienCollision();
        spaceShipSpikeCollision();
        spaceShipBonusCollision();
    }

    private void spaceShipBonusCollision(){
        spaceShipCoinCollision();
        spaceShipTurboCollision();
    }

    private void spaceShipCoinCollision(){
        Group group = levelManager.getCurrentGroup() ;
        for (int j = 0; j < group.getCoins().size(); j++) {
            Coin coin = group.getCoins().get(j);
            if (! coin.isCaught()) {
                if(coin.getX()>ship.getX() && coin.getX()<ship.getX()+ship.getWidth()) {
                    if(coin.getY()>ship.getY() && coin.getY()<ship.getY()+ship.getHeight()) {
                        coin.setCaught(true);
                        player.setCoin(player.getCoin()+1);
                    }
                }

            }

        }
    }


    private void spaceShipTurboCollision(){
        Group group = levelManager.getCurrentGroup() ;
        for (int j = 0; j < group.getTurbos().size(); j++) {
            Turbo turbo = group.getTurbos().get(j);
            if (! turbo.isCaught()) {
                if(turbo.getX()>ship.getX() && turbo.getX()<ship.getX()+ship.getWidth()) {
                    if(turbo.getY()>ship.getY() && turbo.getY()<ship.getY()+ship.getHeight()) {
                        turbo.setCaught(true);
                        turbo.handleJob(ship);
                    }
                }

            }

        }

    }


    private void spaceShipSpikeCollision() {
        Group group = levelManager.getCurrentGroup() ;
        for (int j = 0; j < group.getSpikes().size(); j++) {
            Spike spike = group.getSpikes().get(j);
            if (!spike.isCollided()) {
                if(spike.getX()>ship.getX() && spike.getX()<ship.getX()+ship.getWidth()) {
                    if(spike.getY()>ship.getY() && spike.getY()<ship.getY()+ship.getHeight()) {
                        spike.setCollided(true);
                        player.setPower(player.getPower()-1);
                    }
                }

            }

        }
    }
    private void beamAlienCollision(){
        Group group = levelManager.getCurrentGroup() ;
        for(int i=0 ; i<group.getAliens().size() ; i++) {
            for(int j = 0; j<ship.getBeams().size() ; j++) {
                Alien alien = group.getAliens().get(i);
                Beam beam = ship.getBeams().get(j);
                if(beam.getThrowPermission()) {
                    double d = Math.sqrt(Math.pow(alien.getX()-beam.getX(),2)+Math.pow(alien.getY()-beam.getY(),2)) ;
                    if(alien.isAlive() && d<alien.getHeight()) {
                        beam.setThrowPermission(false);
                        alien.setPower(alien.getPower()-beam.getPower());
                        if(alien.getPower()<=0)
                            alien.gotHit(alien.getX(),alien.getY(),ship.getBeamType());
                    }
                }
            }
        }

    }



    private void spaceShipAlienCollision(){
        Group group = levelManager.getCurrentGroup() ;
        for(Alien alien :group.getAliens()) {
            if(alien.isAlive()) {
                double d = Math.sqrt(Math.pow(alien.getX()-ship.getX(),2)+Math.pow(alien.getY()-ship.getY(),2)) ;
                if(d<Math.min(alien.getHeight() , alien.getWidth())) {
                    timer.stop();
                    contentPane.showGameOverPanel();
                    timer.stop();
                }
            }
        }
    }



    //getters & setters :


    public SpaceShip getShip() {
        return ship;
    }

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public Group getGroup(){
        return levelManager.getCurrentGroup() ;
    }

}