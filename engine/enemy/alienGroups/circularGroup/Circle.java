package engine.enemy.alienGroups.circularGroup;

import engine.enemy.alienGroups.Group;
import engine.enemy.aliens.Alien;
import engine.enemy.aliens.AlienName;
import engine.enemy.aliens.Bloodrex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Circle extends Group {

    int r ;
    final int finalXC, finalYC;
    int xc , yc ;
    int count  ;



    public Circle(int r , int xc , int yc , AlienName name ){
        super(name);
        this.r = r ;
        this.finalXC = xc ;
        this.finalYC = yc ;
        this.xc = -(200+2*r) ;
        this.yc = -(200+2*r) ;
        count = (int) (2*Math.PI*r)/(alien.getWidth()+30) ;
        aliens = new ArrayList<>();

        initialize();
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


    @Override
    public void prepareEntrance() {
        entranceTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                if (xc < finalXC || yc < finalYC) {
                    if (xc < finalXC) xc += 8;
                    if (yc < finalYC) yc += 6;
                    for (int i = 0; i < count; i++) {
                        Alien alien = aliens.get(i);
                        alien.setPolarCoordinates(xc, yc, r, alien.getTeta());
                    }
                }else {
                    entranceTimer.stop();
                    groupReachedDestination = true;
                }
            }

        });

    }



    double dteta = Math.toRadians(1) ;

    @Override
    public void moveGroup() {
        if(groupReachedDestination)
        for(Alien alien : aliens){
            alien.setTeta(alien.getTeta()+dteta);
            alien.setPolarCoordinates(finalXC, finalYC, alien.getR(), alien.getTeta());
            alien.setV(this.finalXC, this.finalYC);
        }
    }


    @Override
    public void renderAliens(Graphics g) {
        super.renderAliens(g);
    }
}
