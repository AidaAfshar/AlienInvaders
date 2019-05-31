package controller.enemy.alienGroups.circularGroup;

import controller.enemy.alienGroups.Group;
import controller.enemy.aliens.Alien;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


public class CircularGroup extends Group {

    ArrayList<Circle> circles = new ArrayList<>();
    Random r = new Random();


    public CircularGroup() {
        super();
        prepareCircles();
        initialize();
    }

    private void prepareCircles() {
        circles.add(new Circle(100, 730, 300, Alien.alien[r.nextInt(4)]));
        circles.add(new Circle(200, 730, 300, Alien.alien[r.nextInt(4)]));
        circles.add(new Circle(300, 730, 300, Alien.alien[r.nextInt(4)]));

        for(Circle circle : circles){
            aliens.addAll(circle.aliens);
        }
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