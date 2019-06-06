package controller.enemy.alienGroups;

import controller.enemy.aliens.Alien;
import controller.enemy.aliens.Augustus;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RandomGroup extends Group{

    Timer moveTimer ;
    Timer chooserTimer ;


    public RandomGroup(){
        this.count = 10 ;
        initialize();
    }

    @Override
    public void initialize(){
        placeAliens();
        prepareChooserTimer();
        chooserTimer.start();
    }

    @Override
    public void placeAliens() {
        for(int i=0 ; i<count ;i++){
            aliens.add(new Augustus(random.nextInt((int)Dim.MAX_X-100),random.nextInt((int)Dim.MAX_Y-100)));
        }
    }

    @Override
    public void prepareEntrance() {

    }

    @Override
    public void moveGroup() {

    }


    public void prepareChooserTimer(){
        chooserTimer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(groupIsDead()) chooserTimer.stop();
                Alien randomAlien = getRandomAlien();
                randomAlien.produceCoordinate();
                int dx = (randomAlien.getNextX() - randomAlien.getX())/300;
                int dy = (randomAlien.getNextY() - randomAlien.getY())/300;
                moveRandomAlien(randomAlien,dx,dy);
            }
        });

    }

    public Alien getRandomAlien(){
        int randomIndex = random.nextInt(aliens.size());
        Alien randomAlien = aliens.get(randomIndex);
        if(randomAlien.isAlive()) return randomAlien ;
        else return getRandomAlien() ;
    }

    public void moveRandomAlien(Alien randomAlien , int dx , int dy) {
        moveTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(randomAlien.isInside()) {
                    if (!randomAlien.reachedNextDestination()) {
                        if (!randomAlien.reachedNextX()) randomAlien.setX(randomAlien.getX() + dx);
                        if (!randomAlien.reachedNextY()) randomAlien.setY(randomAlien.getY() + dy);
                    } else {
                        moveTimer.stop();
                    }
                }else
                 moveTimer.stop();

            }

        });

        moveTimer.start();
    }



    class myTimer extends Thread{

    }

}
