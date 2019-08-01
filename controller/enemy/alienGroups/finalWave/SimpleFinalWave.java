package controller.enemy.alienGroups.finalWave;

import controller.enemy.alienAttack.FinalSpike;
import controller.enemy.aliens.Alien;
import controller.enemy.aliens.Gravitus;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SimpleFinalWave extends FinalWave{


    public SimpleFinalWave(){
        super();
        aliens.add(new Gravitus(Dim.CENTER_X-300,-500));
    }

    @Override
    public void initialize(){
        super.initialize();

    }

    @Override
    public void placeAliens() {

    }


    @Override
    public void prepareEntrance() {
        entranceTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Alien alien : aliens) {
                    if (alien.getY() < Dim.CENTER_Y - 310)
                        alien.setY(alien.getY() + 7);
                    else {
                        evokeSpikeTimer();
                        entranceTimer.stop();
                    }
                }
            }
        });
    }


    @Override
    public void moveGroup() {

    }

    public void releaseSpike(){
        Alien alien = aliens.get(0);
        if(alien.isAlive()) {
            spikes = new ArrayList<> () ;
            spikes.add(new FinalSpike(alien.getX()+90, alien.getY()+90, -5, -5));
            spikes.add(new FinalSpike(alien.getX() + (alien.getWidth() / 2)-10, alien.getY()+10, 0, -5));
            spikes.add(new FinalSpike(alien.getX() + alien.getWidth()-110, alien.getY()+110, 5, -5));
            spikes.add(new FinalSpike(alien.getX() + alien.getWidth()-110, alien.getY() + (alien.getHeight() / 2), 5, 0));
            spikes.add(new FinalSpike(alien.getX() + alien.getWidth()-150, alien.getY() + alien.getHeight()-150, 5, 5));
            spikes.add(new FinalSpike(alien.getX() + (alien.getWidth() / 2)-40, alien.getY() + alien.getHeight()-40, 0, 5));
            spikes.add(new FinalSpike(alien.getX() +140, alien.getY() + alien.getHeight()-140, -5, 5));
            spikes.add(new FinalSpike(alien.getX()+40, alien.getY() + (alien.getHeight() / 2), -5, 0));
        }

    }



}
