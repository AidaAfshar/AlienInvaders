package controller.attackTools;

import java.awt.Graphics;

import view.animations.BombExplosion;
import view.imaging.Image;
import view.imaging.ImageLoader;
import view.utilities.Dim;

public class Bomb extends Beam {

    static String bombImageAddress = "pictures/beams/bomb/bomb.png" ;
//    static String explosionImageAddress = "pictures/beams/bomb/exp.png" ;


    Image explosionImage ;

    public static int count = 0 ;
    final int x0 ;
    final int y0 ;
    int xVector ;
    int yVector ;
    public boolean reachedXCenter = false ;
    public boolean reachedYCenter = false ;
    public boolean explode = false ;



    BombExplosion explosion = new BombExplosion();
    public int j ;
    public boolean timeToKill = false;


    public Bomb(int x0 , int y0) {
        super(bombImageAddress);
        this.x0 = x0 ;
        this.y0 = y0 ;
        initialize();
    }

    public void initialize() {
        this.setAddress( bombImageAddress );
        this.setImage(ImageLoader.Load(bombImageAddress));
        this.width = 150 ;
        this.height = 150 ;
        this.setDimensions();
        this.xVector = getVector(Dim.CENTER_X-x0) ;
        this.yVector = getVector(Dim.CENTER_Y-y0) ;
        this.throwPermission = true ;
//        prepareExplosionImage();
    }


    public static int getVector(int x) {
        if(x>=0) return 1 ;
        else
            return -1 ;
    }


    public void moveBomb() {
        if(Math.abs(x-Dim.CENTER_X)>4) {
            x+= xVector*3 ;
        }else {
            reachedXCenter=true ;
        }
        if(Math.abs(y-Dim.CENTER_Y)>4) {
            y+= yVector*3 ;
        }else {
            reachedYCenter=true ;
        }
        if(reachedXCenter && reachedYCenter) {
            this.explode = true ;
            this.timeToKill = true ;
            this.throwPermission = false ;
        }
    }

//    public void prepareExplosionImage() {
//        explosionImage = new Image();
//        explosionImage.setAddress(explosionImageAddress) ;
//        explosionImage.setImage(ImageLoader.Load(explosionImage.getAddress()));
//    }


    public void renderExplosion(Graphics g) {
        g.drawImage(explosion.explosionImages[j].getImage(),Dim.CENTER_X-120,Dim.CENTER_Y-120,200,200,null);

    }



}
