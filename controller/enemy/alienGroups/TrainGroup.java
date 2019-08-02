package controller.enemy.alienGroups;

import controller.enemy.aliens.*;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Random;

public class TrainGroup extends Group{



    int Xblank ;
    int Yblank ;

    Class[] aliensClass = {Hester.class, Ophelia.class, Augustus.class,Bloodrex.class} ;

    public TrainGroup() {
        super();
        count = 28 ;
        Xblank = 25 ;
        Yblank = 20 ;
//        initialize();
    }

    @Override
    public void initialize(){
        placeAliens();
        prepareEntrance();
    }

    @Override
    public void placeAliens() {
        aliens=new ArrayList<>();


        for (int i=0;i < 7;i++){
            int p = i%7 ;
            aliens.add(new Ophelia(-600 + p * (Xblank + 90), 100));
        }

        for (int i=7 ; i < 14;i++){
            int p = i%7 ;
            aliens.add(new Bloodrex(Dim.MAX_X + p * (Xblank + 90), 250));
        }

        for (int i=14 ;i < 21;i++){
            int p = i%7 ;
            aliens.add(new Hester(-600 + p * (Xblank + 90), 400));
        }

        for (int i=21 ;i < 28;i++){
            int p = i%7 ;
            aliens.add(new Augustus(Dim.MAX_X + p * (Xblank + 90), 550));
        }


        for (Alien alien : aliens)
            alien.setGroup(this);
    }

    @Override
    public void prepareEntrance() {
        moveGroup();
    }

    @Override
    public void moveGroup() {
        for (int i=0;i < 7;i++){
            Alien alien = aliens.get(i) ;
            if(alien.getX()<Dim.MAX_X)
                alien.setX(alien.getX() + 15);
            else{
                int p = i%7 ;
                alien.setX(-600 + p * (Xblank + 90));
            }
        }
        for (int i=7;i < 14;i++){
            Alien alien = aliens.get(i) ;
            if(alien.getX()>0)
                alien.setX(alien.getX() - 15);
            else{
                int p = i%7 ;
                alien.setX(Dim.MAX_X + p * (Xblank + 90));
            }
        }
        for (int i=14;i < 21;i++){
            Alien alien = aliens.get(i) ;
            if(alien.getX()<Dim.MAX_X)
                alien.setX(alien.getX() + 15);
            else{
                int p = i%7 ;
                alien.setX(-600 + p * (Xblank + 90));
            }
        }
        for (int i=21 ; i < 28 ;i++){
            Alien alien = aliens.get(i) ;
            if(alien.getX()>0)
                alien.setX(alien.getX() - 15);
            else{
                int p = i%7 ;
                alien.setX(Dim.MAX_X + p * (Xblank + 90));
            }
        }
    }

    public static Alien loadAlien(Class aClass , int x, int y){

        try {
            System.out.println(aClass.getSimpleName());
            Constructor constructor = aClass.getConstructor (int.class,int.class);
            Alien alien = (Alien) constructor.newInstance (x , y);

            return alien ;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null ;
    }

    public static int produceRandomInt(){
        Random r = new Random() ;
        return r.nextInt(4) ;
    }

    public static int produceRandomY(){
        Random r = new Random() ;
        return r.nextInt(Dim.MAX_Y-50) ;
    }

}
