package controller.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import controller.attackTools.Beam;
import controller.attackTools.Bomb;
import controller.attackTools.FlameBall;
import controller.enemy.alienAttack.Spike;
import controller.enemy.alienGroups.Group;
import controller.enemy.alienGroups.RectangularGroup;
import controller.enemy.alienGroups.circularGroup.CircularGroup;
import controller.enemy.alienGroups.rotatingGroup.RotatingGroup;
import controller.enemy.aliens.Alien;
import controller.enemy.aliens.AlienName;
import controller.player.Player;
import controller.ship.SpaceShip;
import view.imaging.ImageLoader;
import view.screen.GamePanel;

public class Administrator {

    SpaceShip ship ;

    Player player ;
    ArrayList<Group> groups ;
    Group group ;

    Timer timer ;


    public Administrator(Player player) {
        this.player = player ;
        initialize();
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
        group = groups.get(0);
        group.initialize();

    }

    public void prepareShip() {
        ship = new SpaceShip(player);
        ship.setWidth(200);
        ship.setHeight(180) ;
        ship.setDimensions();
        ship.prepareTempTimer();
    }


    public void prepareTimer() {
        timer = new Timer(20,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(group.isDead()) nextGroup();
                group.moveGroup();
                detectCollisions();
                bombExplosion();
                group.produceSpike();
            }

        });
    }

    public void nextGroup(){
        for(Group group : groups){
            if(! group.isDead()){
                this.group = group ;
                group.initialize();
                break ;
            }
        }
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
    }

    public Group getGroup() {
        return group;
    }
}
