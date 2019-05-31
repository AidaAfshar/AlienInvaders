package view.animations;

import java.awt.Graphics;

import view.imaging.Image;
import view.imaging.ImageLoader;
import view.utilities.Dim;


public class BombExplosion {

    //TODO

    int explosionImagesSize = 14 ;
    public Image[] explosionImages ;

    static String explosionPictureAddress_1 = "pictures/beams/bomb/explosion/1.png" ;
    static String explosionPictureAddress_2 = "pictures/beams/bomb/explosion/2.png" ;
    static String explosionPictureAddress_3 = "pictures/beams/bomb/explosion/3.png" ;
    static String explosionPictureAddress_4 = "pictures/beams/bomb/explosion/4.png" ;
    static String explosionPictureAddress_5 = "pictures/beams/bomb/explosion/5.png" ;
    static String explosionPictureAddress_6 = "pictures/beams/bomb/explosion/6.png" ;
    static String explosionPictureAddress_7 = "pictures/beams/bomb/explosion/7.png" ;
    static String explosionPictureAddress_8 = "pictures/beams/bomb/explosion/8.png" ;
    static String explosionPictureAddress_9 = "pictures/beams/bomb/explosion/9.png" ;
    static String explosionPictureAddress_10 = "pictures/beams/bomb/explosion/10.png" ;
    static String explosionPictureAddress_11 = "pictures/beams/bomb/explosion/11.png" ;
    static String explosionPictureAddress_12 = "pictures/beams/bomb/explosion/12.png" ;
    static String explosionPictureAddress_13 = "pictures/beams/bomb/explosion/13.png" ;
    static String explosionPictureAddress_14 = "pictures/beams/bomb/explosion/14.png" ;



    public BombExplosion() {
        initialize();
    }


    public void initialize() {

        explosionImages = new Image[14];
        explosionImages[0] = new Image(explosionPictureAddress_1) ;
        explosionImages[1] = new Image(explosionPictureAddress_2) ;
        explosionImages[2] = new Image(explosionPictureAddress_3) ;
        explosionImages[3] = new Image(explosionPictureAddress_4) ;
        explosionImages[4] = new Image(explosionPictureAddress_5) ;
        explosionImages[5] = new Image(explosionPictureAddress_6) ;
        explosionImages[6] = new Image(explosionPictureAddress_7) ;
        explosionImages[7] = new Image(explosionPictureAddress_8) ;
        explosionImages[8] = new Image(explosionPictureAddress_9) ;
        explosionImages[9] = new Image(explosionPictureAddress_10) ;
        explosionImages[10] = new Image(explosionPictureAddress_11) ;
        explosionImages[11] = new Image(explosionPictureAddress_12) ;
        explosionImages[12] = new Image(explosionPictureAddress_13) ;
        explosionImages[13] = new Image(explosionPictureAddress_14) ;


        for(int i=0 ; i < explosionImagesSize ; i++) {
            explosionImages[i].setImage(ImageLoader.Load(explosionImages[i].getAddress())) ;
        }

    }


    public void renderExplosion(Graphics g,int j) {
        g.drawImage(explosionImages[j].getImage(),Dim.CENTER_X-120,Dim.CENTER_Y-120,250,250,null);
    }

}
