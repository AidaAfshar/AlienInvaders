package controller.attackTools;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.animations.BombExplosion;
import view.imaging.Assets;
import view.imaging.Image;
import view.imaging.ImageLoader;
import view.utilities.Dim;

import javax.swing.*;

public class Bomb extends Beam {

    int vx , vy ;
    boolean reachedXCenter =false;
    boolean reachedYCenter =false;

    boolean explode = false ;


    public Bomb(int x0, int y0) {
        super();
        this.x0 = x0 ;
        this.y0 = y0 ;
        initialize();
    }

    public void initialize() {
        this.setImage(Assets.bombImage);
        setWidth(150) ;
        setHeight(150) ;
        setVx();
        setVy();
        setThrowPermission(true);
        this.setDimensions();
    }

    @Override
    public void moveBeam() {
        setExplode(true);
        setThrowPermission(false);

//        System.out.println("inside moveBomb 1");
//        if(Math.abs(x-Dim.CENTER_X)>4) {
//            x+= vx*3 ;
//        }else {
//            reachedXCenter=true ;
//        }
//        if(Math.abs(y-Dim.CENTER_Y)>4) {
//            y+= vy*3 ;
//        }else {
//            reachedYCenter=true ;
//        }
//        if(reachedXCenter && reachedYCenter) {
//            System.out.println("inside moveBomb 2");
//            setExplode(true);
//            this.throwPermission = false ;
//        }
    }

    Timer expTimer ;
    int i= 0 ;

    public void renderExplosion(Graphics g){
        expTimer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.drawImage(BombExplosion.explosionImages[i],Dim.CENTER_X-120,Dim.CENTER_Y-120,200,200,null);
            }
        });
    }

    //getters & setters:


    public void setVx() {
        vx = x0-Dim.CENTER_X/Math.abs(x0-Dim.CENTER_X);
    }

    public void setVy() {
       vy =  y0-Dim.CENTER_Y/Math.abs(y0-Dim.CENTER_Y);
    }

    public boolean isExploded() {
        return explode;
    }

    public void setExplode(boolean explode) {
        this.explode = explode;
    }


}
