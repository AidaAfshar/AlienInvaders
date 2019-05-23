package fronted.animations;

import java.awt.Graphics;

import fronted.imaging.Image;
import fronted.imaging.ImageLoader;
import fronted.utilities.Dim;


public class Skeleton {

    Image[] skeletonImages ;
    public int j=0 ;

    public Skeleton() {
        initialize();
    }


    public void initialize() {

        skeletonImages = new Image[26];
        for(int i=0 ; i<26 ;i++) {
            skeletonImages[i] = new Image("pictures/aliens/skeleton/skeleton-walking_"+i+".png") ;
        }

        for(int i=0 ; i <26; i++) {
            skeletonImages[i].setImage(ImageLoader.Load(skeletonImages[i].getAddress())) ;
        }

    }

    public void renderMovement(Graphics g,int j) {
        g.drawImage(skeletonImages[j].getImage(),Dim.CENTER_X-150,70,300,300,null);
    }

}
