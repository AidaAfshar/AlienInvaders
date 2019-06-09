package view.animations;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import view.imaging.Assets;
import view.imaging.Image;
import view.imaging.ImageLoader;
import view.utilities.Dim;


public class BombExplosion {


    int explosionImagesSize = 14 ;
    public static BufferedImage[] explosionImages ;

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


    private static BombExplosion ourInstance = new BombExplosion();

    public static BombExplosion getInstance() {
        return ourInstance;
    }

    private BombExplosion() {
        initialize();
    }


    public void initialize() {

        explosionImages = new BufferedImage[14];
        explosionImages[0] = ImageLoader.load(explosionPictureAddress_1) ;
        explosionImages[1] = ImageLoader.load(explosionPictureAddress_2);
        explosionImages[2] = ImageLoader.load(explosionPictureAddress_3) ;
        explosionImages[3] = ImageLoader.load(explosionPictureAddress_4) ;
        explosionImages[4] = ImageLoader.load(explosionPictureAddress_5) ;
        explosionImages[5] = ImageLoader.load(explosionPictureAddress_6) ;
        explosionImages[6] = ImageLoader.load(explosionPictureAddress_7) ;
        explosionImages[7] = ImageLoader.load(explosionPictureAddress_8) ;
        explosionImages[8] = ImageLoader.load(explosionPictureAddress_9) ;
        explosionImages[9] = ImageLoader.load(explosionPictureAddress_10) ;
        explosionImages[10] = ImageLoader.load(explosionPictureAddress_11) ;
        explosionImages[11] = ImageLoader.load(explosionPictureAddress_12) ;
        explosionImages[12] = ImageLoader.load(explosionPictureAddress_13) ;
        explosionImages[13] = ImageLoader.load(explosionPictureAddress_14) ;


    }



}
