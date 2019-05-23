package engine.enemy.alienGroups;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.Timer;

import engine.enemy.alienAttack.Spike;
import engine.enemy.aliens.Alien;
import engine.enemy.aliens.AlienName;
import engine.enemy.aliens.Hester;
import fronted.utilities.Dim;

public class RectangularGroup implements Group{

    Alien alien ;
    ArrayList<Alien> aliens =  new ArrayList<Alien>();
    ArrayList<Spike> spikes = new ArrayList<Spike>();

    Random random = new Random();



    int count =30 ;


    int Xblank = 25 ;
    int Yblank = 20 ;
    int v = 1 ;


    public RectangularGroup(AlienName alienName) {
        this.alien = Alien.diagnoseType(alienName);
        initialize() ;
    }


    public void initialize() {
        placeAliens();
    }

    @Override
    public void placeAliens() {
        if(alien.getName().equals(AlienName.HESTER)) {
            for(int i=0 ; i<count ; i++) {
                int p = i%10 ;
                int q = i/10 ;
                aliens.add(new Hester(100+p*(Xblank+80) , 120+q*(Yblank+100))) ;
            }
        }
    }

    @Override
    public void moveGroup() {
        for(Alien alien : aliens) {

            if( alien.getX()<100 ) v = 1 ;
            else if (alien.getX()>Dim.MAX_X-alien.getWidth()-100) v = -1 ;

            alien.setX( alien.getX() + v ) ;
        }
    }


    public void releaseSpike(int x , int y){
        spikes.add(new Spike(x,y));
    }

    public void produceSpike(){
        for(Alien alien : aliens){
            if(alien.isAlive()) {
                if (random.nextInt(15000) % 1000 == 0) {
                    releaseSpike(alien.getX(), alien.getY());
                }
            }
        }
    }

    int i=0 ;

    @Override
    public void renderGroup(Graphics g) {
        renderAliens(g);
        renderSpikes(g);
    }

    private void renderAliens(Graphics g){
        for(Alien alien : aliens) {
            if(alien.isAlive()) {

                if(i%10==0)
                    moveGroup();

                alien.draw(g);
                i++ ;
            }
        }
    }

    private void renderSpikes(Graphics g){
        for(Spike spike :spikes){
            if(! spike.isCollided()){
                spike.render(g);
                spike.move();
            }
            if(spike.getY()>Dim.MAX_Y+20){
                spike.setCollided(true);
            }
        }
    }


    public ArrayList<Alien> getAliens() {
        return aliens;
    }


    public void setAliens(ArrayList<Alien> aliens) {
        this.aliens = aliens;
    }


    public ArrayList<Spike> getSpikes() {
        return spikes;
    }

    @Override
    public void killTheGroup() {
        for(Alien alien :aliens) {
            alien.gotHit();
        }
    }

}

