package controller.enemy.alienGroups.rotatingGroup;

import controller.enemy.aliens.Alien;
import controller.enemy.aliens.AlienName;
import controller.enemy.aliens.Bloodrex;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Circle {

    Alien alien ;
    ArrayList<Alien> aliens;

    int r ;
    final int initialR , finalR ;
    final int xc , yc ;
    int count ;
    int vr ;

    boolean reachedDestination = false ;

    Timer entranceTimer ;


    public Circle(int initialR ,int finalR , AlienName name){
        this.alien = Alien.diagnoseType(name) ;
        this.initialR = initialR ;
        this.finalR = finalR ;
        this.r = initialR ;
        this.xc = Dim.CENTER_X -80;
        this.yc = Dim.CENTER_Y -80;
        this.count = (int) (2*Math.PI*finalR)/(alien.getWidth()+80) ;

        initialize();
    }

    public void initialize() {
        aliens = new ArrayList<>();
        placeAliens();
    }


    double teta = 0 ;

    public void placeAliens(){
        for(int i=0 ; i<count ; i++){
            Alien alien  = new Bloodrex(xc, yc,initialR,teta);
            alien.setV(xc,yc);
            aliens.add(alien);
            teta += 2*Math.PI/count;
        }
    }

    public void enter() {
        prepareEntrance();
        entranceTimer.start();
    }


    public void prepareEntrance() {
        entranceTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (r > finalR) {
                    r -= 15 ;
                    for (int i = 0; i < count; i++) {
                        Alien alien = aliens.get(i);
                        alien.setPolarCoordinates(xc, yc, r, alien.getTeta());
                    }
                }else {
                    reachedDestination = true;
                }
            }

        });

    }




    double dteta = Math.toRadians(2) ;

    public void move() {
        if(reachedDestination)
            for(Alien alien : aliens){
                alien.setTeta(alien.getTeta()+dteta);
                alien.setPolarCoordinates(xc,yc, alien.getR(), alien.getTeta());
                alien.setV(xc,yc);
            }
    }


}
