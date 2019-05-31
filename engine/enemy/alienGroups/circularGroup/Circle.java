package engine.enemy.alienGroups.circularGroup;

import engine.enemy.aliens.Alien;
import engine.enemy.aliens.AlienName;
import engine.enemy.aliens.Bloodrex;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Circle {

    Alien alien ;
    ArrayList<Alien> aliens;

    int r ;
    final int finalXC, finalYC;
    int xc , yc ;
    int count ;

    boolean reachedDestination = false ;

    Timer entranceTimer ;

    public Circle(int r , int finalXC , int finalYC , AlienName name ){
        this.alien = Alien.diagnoseType(name) ;
        this.r = r ;
        this.finalXC = finalXC ;
        this.finalYC = finalYC ;
        this.xc = -(200+2*r) ;
        this.yc = -(200+2*r) ;
        this.count = (int) (2*Math.PI*r)/(alien.getWidth()+30) ;
        initialize();
    }

    public void initialize() {
        aliens = new ArrayList<>();
        placeAliens();
    }


    double teta = 0 ;

    public void placeAliens(){
        for(int i=0 ; i<count ; i++){
            Alien alien  = new Bloodrex(xc, yc,r,teta);
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

                if (xc < finalXC || yc < finalYC) {
                    if (xc < finalXC) xc += 10;
                    if (yc < finalYC) yc += 6;
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




    double dteta = Math.toRadians(1) ;

    public void move() {
        if(reachedDestination)
        for(Alien alien : aliens){
            alien.setTeta(alien.getTeta()+dteta);
            alien.setPolarCoordinates(finalXC, finalYC, alien.getR(), alien.getTeta());
            alien.setV(this.finalXC, this.finalYC);
        }
    }


}
