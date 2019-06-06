package controller.enemy.alienGroups;

import controller.enemy.alienAttack.Spike;
import controller.enemy.aliens.Alien;
import controller.enemy.aliens.AlienName;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Group {

    protected Alien alien ;
    protected ArrayList<Alien> aliens = new ArrayList<>();
    protected ArrayList<Spike> spikes = new ArrayList<>();

    protected int count  ;
    protected boolean groupReachedDestination ;

    Random random = new Random();

    protected Timer entranceTimer ;


    public Group() {

    }

    public Group(AlienName alienName) {
        this.alien = Alien.diagnoseType(alienName) ;
    }

    public void initialize(){
        placeAliens();
        prepareEntrance();
        entranceTimer.start() ;
    }

    public abstract void placeAliens() ;
    public abstract void prepareEntrance() ;
    public abstract void moveGroup() ;




    public void produceSpike(){
        for(Alien alien : aliens){
            if(alien.isAlive()) {
                //TODO
                if (random.nextInt(15000) % 1000 == 0) {
                    releaseSpike(alien.getX(), alien.getY());
                }
            }
        }
    }

    public void releaseSpike(int x , int y){
        spikes.add(new Spike(x,y));
    }


//    public void check(){
//        for(Alien alien : aliens){
//            System.out.println(alien.getX());
//            System.out.println(alien.getY());
//        }
//        System.out.println("----------------------------------------------------");
//    }


    int i=0 ;

    public void renderGroup(Graphics g) {
        renderAliens(g);
        if(groupReachedDestination)
            renderSpikes(g);

    }

    protected void renderAliens(Graphics g){
        for(Alien alien : aliens) {
            if(alien.isAlive()) {
                alien.draw(g);
                i++ ;
            }
        }
    }

    protected void renderSpikes(Graphics g){
        for(Spike spike :spikes){
            if(! spike.isCollided()){
                spike.render(g);
                spike.move();
            }
            if(spike.getY()> Dim.MAX_Y+20){
                spike.setCollided(true);
            }
        }
    }


    public boolean groupIsDead() {

        for(Alien alien : aliens) {
            if(alien.isAlive())
                return false ;
        }

        return true ;
    }

    public void killTheGroup() {
        for(Alien alien :aliens) {
            alien.gotHit();
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

    public Alien getAlien() {
        return alien;
    }


}
