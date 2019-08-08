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
        if(Math.abs(x-Dim.CENTER_X)>4) {
            x+= vx*3 ;
        }else {
            reachedXCenter=true ;
        }
        if(Math.abs(y-Dim.CENTER_Y)>4) {
            y+= vy*3 ;
        }else {
            reachedYCenter=true ;
        }
        if(reachedXCenter && reachedYCenter) {
            System.out.println("inside reached");
            this.explode = true ;
            this.throwPermission = false ;
        }


    }

    Timer expTimer ;
    int i= 0 ;

    public void renderExplosion(Graphics g){
        expTimer = new Timer(40, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(i>13){
                    expTimer.stop();
                    return;
                }
                System.out.println(i);
                g.drawImage(BombExplosion.explosionImages[i],Dim.CENTER_X-120,Dim.CENTER_Y-120,200,200,null);
                i++ ;
            }
        });
        expTimer.start();
    }

    //getters & setters:


    public void setVx() {
        vx = (Dim.CENTER_X-x0)/Math.abs(x0-Dim.CENTER_X);
    }

    public void setVy() {
       vy =  (Dim.CENTER_Y-y0)/Math.abs(y0-Dim.CENTER_Y);
    }

    public boolean isExploded() {
        return explode;
    }

    public void setExplode(boolean explode) {
        this.explode = explode;
    }


}
//
//    public static int count = 0 ;
//    final int x0 ;
//    final int y0 ;
//    int xVector ;
//    int yVector ;
//    public boolean reachedXCenter = false ;
//    public boolean reachedYCenter = false ;
//    public boolean explode = false ;
//
//
//
//    BombExplosion explosion = new BombExplosion();
//    public int j ;
//    public boolean timeToKill = false;
//
//
//    public Bomb(int x0 , int y0) {
//        super();
//        this.x0 = x0 ;
//        this.y0 = y0 ;
//        initialize();
//    }
//
//    public void initialize() {
//        this.setImage(Assets.diamondImage);
//        this.width = 150 ;
//        this.height = 150 ;
//        this.setDimensions();
//        this.xVector = getVector(Dim.CENTER_X-x0) ;
//        this.yVector = getVector(Dim.CENTER_Y-y0) ;
//        this.throwPermission = true ;
////        prepareExplosionImage();
//    }
//
//
//    public static int getVector(int x) {
//        if(x>=0) return 1 ;
//        else
//            return -1 ;
//    }
//
//
//    public void moveBomb() {
//        System.out.println("inside moveBomb 1");
//        if(Math.abs(x-Dim.CENTER_X)>4) {
//            x+= xVector*3 ;
//        }else {
//            reachedXCenter=true ;
//        }
//        if(Math.abs(y-Dim.CENTER_Y)>4) {
//            y+= yVector*3 ;
//        }else {
//            reachedYCenter=true ;
//        }
//        if(reachedXCenter && reachedYCenter) {
//            System.out.println("inside moveBomb 2");
//            this.explode = true ;
//            this.timeToKill = true ;
//            this.throwPermission = false ;
//        }
//    }
//
////    public void prepareExplosionImage() {
////        explosionImage = new Image();
////        explosionImage.setAddress(explosionImageAddress) ;
////        explosionImage.setImage(ImageLoader.Load(explosionImage.getAddress()));
////    }
//
//
//    public void renderExplosion(Graphics g) {
//        g.drawImage(explosion.explosionImages[j].getImage(),Dim.CENTER_X-120,Dim.CENTER_Y-120,200,200,null);
//
//    }
//
//
//    @Override
//    public void draw(Graphics g){
//        super.draw(g);
//        System.out.println("inside draw");
//    }
