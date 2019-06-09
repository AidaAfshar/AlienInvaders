package controller.enemy.alienGroups.rotatingGroup;

import controller.enemy.alienGroups.Group;
import controller.enemy.alienGroups.GroupType;
import controller.enemy.aliens.Alien;
import controller.enemy.aliens.AlienName;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RotatingGroup extends Group {

    ArrayList<Circle> circles ;

    public RotatingGroup() {
        super();
        setType(GroupType.ROTATING);
        prepareCircles();
//        initialize();
    }

    public void prepareCircles(){
        circles = new ArrayList<>() ;
        circles.add(new Circle(1000 , 220 , AlienName.BLOODREX));
        circles.add(new Circle(1200 , 320 , AlienName.BLOODREX));
        circles.add(new Circle(1500 , 420 , AlienName.BLOODREX));

        for(Circle circle : circles)
            aliens.addAll(circle.aliens);


        for(Alien alien : aliens)
            alien.setGroup(this);
    }

    public void placeAliens() {
        for (Circle circle : circles) {
            circle.placeAliens();
        }
    }


    public void prepareEntrance() {

        for (Circle circle : circles)
            circle.enter();

        entranceTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int i = 0;
                for (Circle circle : circles) {
                    if (!circle.reachedDestination) break;
                    else i++;
                }

                if (i == 3) {
                    groupReachedDestination = true;
                    entranceTimer.stop();
                }

            }
        });

    }


    @Override
    public void moveGroup() {
        for (Circle circle : circles) {
            circle.move();
        }
    }
}
