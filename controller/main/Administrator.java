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

    public GamePanel gamePanel ;

    public SpaceShip ship ;
    public Group group ;
    public Player player ;

    static Timer timer ;


    public Administrator(GamePanel gamePanel,SpaceShip spaceShip,Player player) {
        this.gamePanel = gamePanel ;
        this.ship = spaceShip ;
        this.player = player ;
        initialize();
    }



    private void initialize() {
        prepareShip();
        prepareEnemy();
        prepareTimer();
        timer.start();
    }


    public void prepareTimer() {
        timer = new Timer(20,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                group.moveGroup();
                ship.shipAttack.attack();
                ship.tempController.controlTemp(gamePanel);
                detectCollisions();
                bombExplosion();
                group.produceSpike();
                updateValues();

            }

        });
    }


    protected void bombExplosion() {
        for(Bomb bomb : ship.shipAttack.getBombs()) {
            if(bomb.timeToKill)
                group.killTheGroup();
        }
    }



    public void prepareShip() {
        ship.setImage(ImageLoader.Load(ship.getAddress()));
        ship.bml = gamePanel.bml ;
        ship.setWidth(200);
        ship.setHeight(180) ;
        ship.setDimensions();
        ship.tempController.prepareTempTimer();

    }

    private void prepareEnemy() {
       group = new RectangularGroup(AlienName.AUGUSTUS) ;
//       group = new CircularGroup() ;
//       group = new RotatingGroup() ;
//       group = new RandomGroup(ship) ;
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
                        ship.power-- ;
                    }
                }

            }

        }
    }
    private void beamAlienCollision(){
        for(int i=0 ; i<group.getAliens().size() ; i++) {
            for(int j = 0; j<ship.shipAttack.getBeams().size() ; j++) {
                Alien alien = group.getAliens().get(i);
                Beam beam = ship.shipAttack.getBeams().get(j);
                if(beam.getThrowPermission()==true) {
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
        gamePanel.tempBar.setValue(ship.tempController.temperature);
        gamePanel.bombLabel.setText("         Bomb : " + ship.bombCount);
        gamePanel.powerLabel.setText("      Power : " + ship.power);
        gamePanel.coinLabel.setText("   Coin : " + ship.coin);
    }

}
