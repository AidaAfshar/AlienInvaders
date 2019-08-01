package controller.enemy.alienGroups.finalWave;

import controller.enemy.alienAttack.FinalSpike;
import controller.enemy.aliens.Alien;
import controller.enemy.aliens.Gravitus;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoundFinalWave extends FinalWave {


    public RoundFinalWave(){
        super();
        Alien alien = new Gravitus (-500,0) ;
        alien.setWidth (250);
        alien.setHeight (250);
        aliens.add(alien);
        delay = 1000 ;
    }

    @Override
    public void placeAliens() {

    }


    @Override
    public void prepareEntrance() {
        entranceTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Alien alien : aliens){
                    if(alien.getX () < 0){
                        alien.setX ( alien.getX() + 7 );
                    }else{
                        evokeSpikeTimer();
                        moveGroup();
                        entranceTimer.stop();
                    }
                }
            }
        });
    }

    String place = "out";

    @Override
    public void moveGroup() {
        Alien alien = aliens.get(0);
        if(alien.isAlive()) {
            if (alien.getY () == 0 && alien.getX () < Dim.MAX_X - alien.getWidth ()) {
                alien.setX (alien.getX () + 8);
                place = "up";
            } else if (alien.getX () >= Dim.MAX_X - alien.getWidth () && alien.getY () < Dim.MAX_Y - alien.getHeight ()) {
                alien.setY (alien.getY () + 8);
                place = "right";
            } else if (alien.getY () >= Dim.MAX_Y - alien.getHeight () && alien.getX () > 0) {
                alien.setX (alien.getX () - 8);
                place = "down";
            } else if (alien.getX () <= 0 && alien.getY () > 0) {
                alien.setY (alien.getY () - 8);
                place = "left";
            }
        }
    }


    @Override
    public void releaseSpike() {
        Alien alien = aliens.get (0) ;
        if(place.equals ("up")){
            spikes = new ArrayList<> () ;
            spikes.add(new FinalSpike(alien.getX() + alien.getWidth()-110, alien.getY() + (alien.getHeight() / 2), 5, 0));
            spikes.add(new FinalSpike(alien.getX() + alien.getWidth()-150, alien.getY() + alien.getHeight()-150, 5, 5));
            spikes.add(new FinalSpike(alien.getX() + (alien.getWidth() / 2)-40, alien.getY() + alien.getHeight()-40, 0, 5));
            spikes.add(new FinalSpike(alien.getX() +140, alien.getY() + alien.getHeight()-140, -5, 5));
            spikes.add(new FinalSpike(alien.getX()+40, alien.getY() + (alien.getHeight() / 2), -5, 0));
        }else if(place.equals ("right")){
            spikes = new ArrayList<> () ;
            spikes.add(new FinalSpike(alien.getX()+90, alien.getY()+90, -5, -5));
            spikes.add(new FinalSpike(alien.getX() + (alien.getWidth() / 2)-10, alien.getY()+10, 0, -5));
            spikes.add(new FinalSpike(alien.getX() + (alien.getWidth() / 2)-40, alien.getY() + alien.getHeight()-40, 0, 5));
            spikes.add(new FinalSpike(alien.getX() +140, alien.getY() + alien.getHeight()-140, -5, 5));
            spikes.add(new FinalSpike(alien.getX()+40, alien.getY() + (alien.getHeight() / 2), -5, 0));
        }else if(place.equals ("down")){
            spikes = new ArrayList<> () ;
            spikes.add(new FinalSpike(alien.getX()+90, alien.getY()+90, -5, -5));
            spikes.add(new FinalSpike(alien.getX() + (alien.getWidth() / 2)-10, alien.getY()+10, 0, -5));
            spikes.add(new FinalSpike(alien.getX() + alien.getWidth()-110, alien.getY()+110, 5, -5));
            spikes.add(new FinalSpike(alien.getX() + alien.getWidth()-110, alien.getY() + (alien.getHeight() / 2), 5, 0));
            spikes.add(new FinalSpike(alien.getX()+40, alien.getY() + (alien.getHeight() / 2), -5, 0));
        }else if(place.equals ("left")){
            spikes = new ArrayList<> () ;
            spikes.add(new FinalSpike(alien.getX()+90, alien.getY()+90, -5, -5));
            spikes.add(new FinalSpike(alien.getX() + (alien.getWidth() / 2)-10, alien.getY()+10, 0, -5));
            spikes.add(new FinalSpike(alien.getX() + alien.getWidth()-110, alien.getY() + (alien.getHeight() / 2), 5, 0));
            spikes.add(new FinalSpike(alien.getX() + alien.getWidth()-150, alien.getY() + alien.getHeight()-150, 5, 5));
            spikes.add(new FinalSpike(alien.getX() + (alien.getWidth() / 2)-40, alien.getY() + alien.getHeight()-40, 0, 5));
        }

    }
}
