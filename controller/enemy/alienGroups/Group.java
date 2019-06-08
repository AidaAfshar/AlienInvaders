package controller.enemy.alienGroups;

import controller.bonus.Bonus;
import controller.bonus.Coin;
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
    protected ArrayList<Bonus> bonus = new ArrayList<>();

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

    public void moveStuffs(){
        moveGroup();
        moveSpikes();
        moveBonus();
    }

    private void moveSpikes(){
        for(Spike spike : spikes){
            spike.move();
        }
    }

    private void moveBonus(){
        for(Bonus bonus : bonus){
            bonus.move();
        }
    }

    // SPIKE :

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

    // BONUS :

    public void releaseBonus(int x , int y){
        int num = random.nextInt(12) + 1;
        if(num%3==0)
            releaseCoin(x,y);
        if(num%4==0)
            releaseTurbo(x,y);
    }

    public void releaseCoin(int x , int y){
        bonus.add(new Coin(x,y));
    }

    public void releaseTurbo(int x , int y){

    }



    int i=0 ;

    public void renderGroup(Graphics g) {
        renderAliens(g);
        if(groupReachedDestination) {
            renderSpikes(g);
            renderBonus(g);
        }

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
                spike.draw(g);
            //    spike.move();
            }
            if(spike.getY()> Dim.MAX_Y+20){
                spike.setCollided(true);
            }
        }
    }

    protected void renderBonus(Graphics g){
        for(Bonus bonus :bonus){
            if(! bonus.isCaught()){
                bonus.draw(g);
            //    bonus.move();
            }
            if(bonus.getY()> Dim.MAX_Y+20){
                bonus.setCaught(true);
            }
        }
    }


    public boolean isDead() {

        for(Alien alien : aliens) {
            if(alien.isAlive())
                return false ;
        }

        return true ;
    }

    public void killTheGroup() {
        for(Alien alien :aliens) {
            alien.setAlive(false);
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
