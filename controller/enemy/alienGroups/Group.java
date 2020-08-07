package controller.enemy.alienGroups;

import controller.attackTools.BeamType;
import controller.bonus.Coin;
import controller.bonus.empowerment.Turbo;
import controller.bonus.empowerment.TempInterval;
import controller.enemy.alienAttack.Spike;
import controller.enemy.alienGroups.circularGroup.CircularGroup;
import controller.enemy.alienGroups.finalWave.FinalWave;
import controller.enemy.alienGroups.finalWave.SimpleFinalWave;
import controller.enemy.alienGroups.rotatingGroup.RotatingGroup;
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

    protected int count  ;
    protected boolean groupReachedDestination ;

    Random random = new Random();

    protected Timer entranceTimer ;

    GroupType type ;


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

    private void moveBonus(){
        moveCoins();
        moveTurbos();
    }

    private void moveSpikes(){
        for(Spike spike : spikes){
            spike.move();
        }
    }

    private void moveCoins(){
        for(Coin coin : coins){
            coin.move();
        }
    }

    private void moveTurbos(){
        for(Turbo turbo : turbos){
            turbo.move();
        }
    }

    // SPIKE :

    protected ArrayList<Spike> spikes = new ArrayList<>();

    public void produceSpike(){
        for(Alien alien : aliens){
            if(alien.isAlive()) {
                if (random.nextInt(15000) % alien.getProbability() == 0) {
                    releaseSpike(alien.getX(), alien.getY());
                }
            }
        }
    }

    public void releaseSpike(int x , int y){
        spikes.add(new Spike(x,y));
    }

    // BONUS :

    protected ArrayList<Turbo> turbos = new ArrayList<>();
    protected ArrayList<Coin> coins = new ArrayList<>();


    public void releaseBonus(int x , int y , BeamType type){
        int num = random.nextInt(12) + 1;
        if(num%3==0)
            releaseCoin(x,y);
        if(num%4==0)
            releaseTurbo(x,y,type);
    }

    public void releaseCoin(int x , int y){
        coins.add(new Coin(x,y));
    }

    public void releaseTurbo(int x , int y ,BeamType type){
        int num = random.nextInt(3);
        if(num == 0)
            turbos.add(new TempInterval(x,y));

        if(num != 0)
            turbos.add(Turbo.diagnoseTurbo(x,y,type));

    }


    public static Group diagnoseType(GroupType type){
        if(type.equals(GroupType.RECTANGULAR))
            return new RectangularGroup() ;

        if(type.equals(GroupType.CIRCULAR))
            return new CircularGroup();

        if(type.equals(GroupType.ROTATING))
            return new RotatingGroup() ;

        if(type.equals(GroupType.TRAIN))
            return new TrainGroup() ;

        if(type.equals(GroupType.FINALWAVE))
            return new SimpleFinalWave() ;

        else return null ;
    }

    //----

    int i=0 ;

    public void renderGroup(Graphics g) {
        renderAliens(g);
        if(groupReachedDestination) {
            renderSpikes(g);
            renderTurbos(g);
            renderCoins(g);
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
            if(! spike.isCollided())
                spike.draw(g);

            if(spike.getY()> Dim.MAX_Y+20){
                spike.setCollided(true);
            }
        }
    }

    protected void renderTurbos(Graphics g){
        for(Turbo turbo : turbos){
            if(! turbo.isCaught())
                turbo.draw(g);

            if(turbo.getY()> Dim.MAX_Y+20){
                turbo.setCaught(true);
            }
        }
    }

    protected void renderCoins(Graphics g){
        for(Coin coin:coins){
            if(! coin.isCaught() && coin.getShowPermition())
                coin.draw(g);

            if(coin.getY()> Dim.MAX_Y+20){
                coin.setCaught(true);
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

    public ArrayList<Turbo> getTurbos() {
        return turbos;
    }

    public void setTurbos(ArrayList<Turbo> turbo) {
        this.turbos = turbo;
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public void setCoins(ArrayList<Coin> coins) {
        this.coins = coins;
    }

    public GroupType getType() {
        return type;
    }

    public void setType(GroupType type) {
        this.type = type;
    }


}
