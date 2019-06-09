package controller.enemy.alienGroups;

import controller.enemy.alienAttack.FinalSpike;
import controller.enemy.aliens.Alien;
import controller.enemy.aliens.Gravitus;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalWave  extends Group{

    Alien alien ;
    Timer spikeTimer ;

    public FinalWave() {
        super();

    }

    @Override
    public void placeAliens() {
        alien = new Gravitus(Dim.CENTER_X,-300) ;
        aliens.add(alien);
    }

    @Override
    public void prepareEntrance() {
        entranceTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (alien.getY() < Dim.CENTER_Y - alien.getHeight())
                    alien.setY(alien.getY() + 5);
                else
                    entranceTimer.stop();
            }
        });

    }

    @Override
    public void moveGroup() {

    }

    @Override
    public void produceSpike() {

    }

    public void evokeSpikeTimer(){
        spikeTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                releaseSpike();
            }
        });

        spikeTimer.start();
    }

    public void releaseSpike(){
        spikes.add(new FinalSpike(alien.getX(),alien.getY(),-7,-7));
        spikes.add(new FinalSpike(alien.getX()+(alien.getWidth()/2),alien.getY(),0,-5));
        spikes.add(new FinalSpike(alien.getX()+alien.getWidth(),alien.getY(),7,-7));
        spikes.add(new FinalSpike(alien.getX()+alien.getWidth(),alien.getY()+(alien.getHeight()/2),5,0));
        spikes.add(new FinalSpike(alien.getX()+alien.getWidth(),alien.getY()+alien.getHeight(),7,7));
        spikes.add(new FinalSpike(alien.getX()+(alien.getWidth()/2),alien.getY()+alien.getHeight(),0,5));
        spikes.add(new FinalSpike(alien.getX(),alien.getY()+alien.getHeight(),-7,7));
        spikes.add(new FinalSpike(alien.getX()+(alien.getHeight()/2),alien.getY(),-5,0));
    }

}
