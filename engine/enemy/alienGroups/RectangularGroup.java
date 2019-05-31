package engine.enemy.alienGroups;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import engine.enemy.aliens.*;
import fronted.utilities.Dim;

public class RectangularGroup extends Group {

    int Xblank ;
    int Yblank ;
    int v = 1 ;



    public RectangularGroup(AlienName name) {
        super(name);
        count = 30 ;
        Xblank = 25 ;
        Yblank = 20 ;
        initialize();
    }


    @Override
    public void placeAliens() {
        for(int i=0 ; i<count ; i++) {
                int p = i%10 ;
                int q = i/10 ;
                aliens.add(new Bloodrex(-1700 + p * (Xblank + alien.getWidth()), -480 + q * (Yblank + alien.getHeight())));
            }

    }

    @Override
    public void moveGroup() {
        for(Alien alien : aliens) {

            if( alien.getX()<100 ) v=1 ;
            else if (alien.getX()>Dim.MAX_X-alien.getWidth()-100) v=-1 ;

            alien.setX( alien.getX() + v ) ;
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
                        if (alien.getX() < 100 + p * (Xblank + alien.getWidth())) alien.setX(alien.getX() + 6);
                        if (alien.getY() < 120 + q * (Yblank + alien.getHeight())) alien.setY(alien.getY() + 5);
                    }
                else
                    entranceTimer.stop();

            }

        });
    }


    @Override
    public void renderAliens(Graphics g) {
//        m = 10 ;
        super.renderAliens(g);
    }

}

