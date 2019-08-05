package controller.attackTools;

import view.imaging.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MultiArrows {

    Beam[] beams =  new Beam[5];


    int x0 , y0 ;

    public MultiArrows(int x0, int y0) {
        super();
        this.x0 = x0 ;
        this.y0 = y0 ;
        initialize();
    }

    public void initialize() {
        setImages() ;
        setOtherFeatures() ;
    }


    void setImages(){

        for (int i=0 ; i<5 ; i++)
            beams[i] = new Beam(x0 , y0) ;

        beams[0].setImage(Assets.multi1Image) ;
        beams[1].setImage(Assets.multi2Image) ;
        beams[2].setImage(Assets.multi3Image) ;
        beams[3].setImage(Assets.multi4Image) ;
        beams[4].setImage(Assets.multi5Image) ;

    }

    void setOtherFeatures(){

        for (int i=0 ; i<5 ; i++){
            beams[i].setWidth(70);
            beams[i].setHeight(70);
            beams[i].setPower(1);
            beams[i].setTempUp(1);
            beams[i].setThrowPermission(true);
            beams[i].setDimensions();
        }
        beams[2].setWidth(25);
    }

    public void moveBeam() {
        for (int i=0 ; i<5 ; i++){
            beams[i].y-=7;
            if(beams[i].y < -20)
                beams[i].setThrowPermission(false);
        }

        beams[0].x -= 4 ;
        beams[1].x -= 2 ;
        beams[3].x += 2 ;
        beams[4].x += 4 ;
    }


    public void draw(Graphics g) {
        for (int i=0 ; i<5 ; i++)
            beams[i].draw(g);
    }



    public BufferedImage[] getImages(){

        BufferedImage[] images = new BufferedImage[5] ;
        for (int i=0 ; i<5 ; i++){
            images[i] = beams[i].getImage() ;
        }

        return images ;
    }

    public Beam[] getBeams(){
        return beams ;
    }

}
