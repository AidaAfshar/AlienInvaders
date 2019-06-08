package controller.enemy.alienGroups;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import controller.enemy.aliens.*;
import view.utilities.Dim;

public class RectangularGroup extends Group {

    int Xblank ;
    int Yblank ;
    int v = 1 ;



    public RectangularGroup() {
        super(AlienName.HESTER);
        count = 30 ;
        Xblank = 25 ;
        Yblank = 20 ;
//        initialize();
    }


    @Override
    public void placeAliens() {
        for(int i=0 ; i<count ; i++) {
                int p = i%10 ;
                int q = i/10 ;
                aliens.add(new Hester(-1700 + p * (Xblank + alien.getWidth()), -480 + q * (Yblank + alien.getHeight())));
            }

    }


    @Override
    public void prepareEntrance(){
        entranceTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (aliens.get(0).getX() < 100 || aliens.get(0).getY() < 120)
                    for (int i = 0; i < count; i++) {
                        Alien alien = aliens.get(i);
                        int p = i % 10;
                        int q = i / 10;
                        if (alien.getX() < 100 + p * (Xblank + alien.getWidth())) alien.setX(alien.getX() + 4);
                        if (alien.getY() < 120 + q * (Yblank + alien.getHeight())) alien.setY(alien.getY() + 4);
                    }
                else {
                    groupReachedDestination = true ;
                    entranceTimer.stop();
                }
            }

        });
    }


    @Override
    public void moveGroup() {

        for(Alien alien : aliens) {

            if( alien.getX()<100 ) v=7 ;
            else if (alien.getX()>Dim.MAX_X-alien.getWidth()-100) v=-7 ;

            alien.setX( alien.getX() + v ) ;
        }
    }

    @Override
    public void renderAliens(Graphics g) {
        super.renderAliens(g);
    }

}

