package controller.main.administrator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import controller.attackTools.Beam;
import controller.attackTools.Bomb;
import controller.bonus.Coin;
import controller.bonus.empowerment.Turbo;
import controller.enemy.alienAttack.Spike;
import controller.enemy.alienGroups.FinalWave;
import controller.enemy.alienGroups.Group;
import controller.enemy.alienGroups.GroupType;
import controller.enemy.alienGroups.RectangularGroup;
import controller.enemy.alienGroups.circularGroup.CircularGroup;
import controller.enemy.alienGroups.rotatingGroup.RotatingGroup;
import controller.enemy.aliens.Alien;
import controller.player.Player;
import controller.ship.SpaceShip;
import view.screen.ContentPane;

public class SinglePlayerAdministrator {

    SpaceShip ship ;

    Player player ;
    ArrayList<Group> groups ;
    Group group ;

    Timer timer ;

    ContentPane contentPane ;


    public SinglePlayerAdministrator(ContentPane contentPane) {
        this.contentPane = contentPane ;
        //    initialize();
    }


    private void initialize(){
        prepareEnemy();
        prepareShip();
        prepareTimer();
        timer.start();
    }



    private void prepareEnemy() {
//       group = new RandomGroup(ship) ;

        groups = new ArrayList<>();
        groups.add(new RectangularGroup());
        groups.add(new CircularGroup());
        groups.add(new RotatingGroup());
        groups.add(new FinalWave());
        groups.add(new RectangularGroup());
        groups.add(new CircularGroup());
        groups.add(new RotatingGroup());
        groups.add(new FinalWave());
        groups.add(new RectangularGroup());
        groups.add(new CircularGroup());
        groups.add(new RotatingGroup());
        groups.add(new FinalWave());
        groups.add(new RectangularGroup());
        groups.add(new CircularGroup());
        groups.add(new RotatingGroup());
        groups.add(new FinalWave());

        group = groups.get(0);
        group.initialize();

    }

    public void prepareShip() {
        ship = player.getShip();
    }


    public void prepareTimer() {
        timer = new Timer(20,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(group.isDead())
                    nextGroup();
                group.moveStuffs();
                group.produceSpike();
                detectCollisions();
                bombExplosion();
            }

        });
    }

    int round = 0 ;

    public void nextGroup(){
        for(Group group : groups){
            if(! group.isDead()){
                this.group = group ;
                this.group.initialize();
                break ;
            }else if(group.getType().equals(GroupType.FINALWAVE)){
                round++ ;
                if(round == 4) finishGame();
            }
        }
    }


    public void finishGame(){
        contentPane.showWinPanel(player.getCoin());
        timer.stop();
    }

    protected void bombExplosion() {
        for(Bomb bomb : ship.getBombs()) {
            if(bomb.isExploded()){
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        initialize();
    }

    public Group getGroup() {
        return group;
    }
}