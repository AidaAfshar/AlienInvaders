package controller.enemy.alienGroups.finalWave;

import controller.enemy.alienGroups.Group;
import controller.enemy.alienGroups.GroupType;
import controller.enemy.aliens.Gravitus;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class FinalWave extends Group {

    Timer spikeTimer ;

    public FinalWave() {
        super();
        setType(GroupType.FINALWAVE);
    }



    public abstract void placeAliens() ;


    public abstract void prepareEntrance() ;


    public abstract void moveGroup() ;


    @Override
    public void produceSpike() {

    }

    int delay = 2000 ;

    public void evokeSpikeTimer(){
        spikeTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                releaseSpike();
            }
        });

        spikeTimer.start();
    }


    public abstract void releaseSpike() ;


    @Override
    public void renderGroup(Graphics g){
        renderAliens(g);
        renderSpikes(g);
    }

}
