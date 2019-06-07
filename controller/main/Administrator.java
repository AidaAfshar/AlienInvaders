package controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import controller.attackTools.Beam;
import controller.attackTools.Bomb;
import controller.attackTools.FlameBall;
import controller.enemy.alienAttack.Spike;
import controller.enemy.alienGroups.Group;
import controller.enemy.alienGroups.RectangularGroup;
import controller.enemy.aliens.Alien;
import controller.enemy.aliens.AlienName;
import controller.player.Player;
import controller.ship.SpaceShip;
import view.imaging.ImageLoader;
import view.screen.GamePanel;

public class Administrator {

    GamePanel gamePanel ;
    SpaceShip ship ;

    Player player ;
    Group group ;

    Timer timer ;


    public Administrator(GamePanel gamePanel) {
        this.gamePanel = gamePanel ;
        initialize();
    }


    private void initialize(){
//        preparePlayer();
        prepareEnemy();
        prepareShip();
        prepareTimer();
        timer.start();
    }


    public void preparePlayer(String name){
        player = new Player(name);
    }

    private void prepareEnemy() {
        group = new RectangularGroup(AlienName.AUGUSTUS) ;
//       group = new CircularGroup() ;
//       group = new RotatingGroup() ;
//       group = new RandomGroup(ship) ;
    }

    public void prepareShip() {
        ship = new SpaceShip();
        ship.bml = gamePanel.bml ;
        ship.setWidth(200);
        ship.setHeight(180) ;
        ship.setDimensions();
        ship.prepareTempTimer();
    }


    public void prepareTimer() {
        timer = new Timer(20,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                group.moveGroup();
                ship.attack();
                ship.controlTemp(gamePanel);
                detectCollisions();
                bombExplosion();
                group.produceSpike();
                updateValues();

            }

        });
    }


    protected void bombExplosion() {
        for(Bomb bomb : ship.getBombs()) {
            if(bomb.timeToKill)
                group.killTheGroup();
        }
    }






    public void detectCollisions() {
        beamAlienCollision();
        spaceShipAlienCollision();
        spaceShipSpikeCollision();
    }

    private void spaceShipSpikeCollision() {
        for (int j = 0; j < group.getSpikes().size(); j++) {
            Spike spike = group.getSpikes().get(j);
            if (!spike.isCollided()) {
                if(spike.getX()>ship.getX() && spike.getX()<ship.getX()+ship.getWidth()) {
                    if(spike.getY()>ship.getY() && spike.getY()<ship.getY()+ship.getHeight()) {
                        spike.setCollided(true);
                        ship.setPower(ship.getPower()-1);
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
                        alien.gotHit();
                        beam.setThrowPermission(false);
                    }
                }
            }
        }

    }



    private void spaceShipAlienCollision(){

        //TODO


//		for(Alien alien :rect.getAliens()) {
//			if(alien.isAlive()) {
//				double d = Math.sqrt(Math.pow(alien.getX()-ship.getX(),2)+Math.pow(alien.getY()-ship.getY(),2)) ;
//				if(d<alien.getHeight() || d<alien.getWidth()) {
//					gamePanel.ml.setClickCount(0);
//					ship.setY(ship.getY()+100);
//					ship.power -- ;
//				}
//			}
//		}
    }



    public void updateValues() {
        gamePanel.tempBar.setValue(ship.getTemperature());
        gamePanel.bombLabel.setText("         Bomb : " + ship.getBombCount());
        gamePanel.powerLabel.setText("      Power : " + ship.getPower());
        gamePanel.coinLabel.setText("   Coin : " + ship.getCoin());
    }


    //getters & setters :


    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

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
    }
}
