package engine.enemy.alienGroups.circularGroup;

import engine.enemy.alienGroups.Group;
import engine.enemy.aliens.Alien;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class CircularGroup extends Group {

    ArrayList<Circle> circles ;
    Random r = new Random();

    public CircularGroup() {
        super();
        initialize();
    }

    public void initialize(){
        circles = new ArrayList<>() ;
        circles.add(new Circle(200 , 650 , 500 , Alien.alien[r.nextInt(4)])) ;
        circles.add(new Circle(300 , 650 , 500 , Alien.alien[r.nextInt(4)])) ;
        circles.add(new Circle(400 , 650 , 500 , Alien.alien[r.nextInt(4)])) ;
    }

    @Override
    public void prepareEntrance() {

    }

    @Override
    public void placeAliens() {

    }

    @Override
    public void moveGroup() {

    }


}
