package controller.attackTools;

import view.imaging.Assets;
import view.imaging.Image;

import java.awt.*;

public class MultiBeam extends Beam{


//    public MultiBeam(int x0, int y0) {
//        super();
//        this.x0 = x0 ;
//        this.y0 = y0 ;
//        initialize();
//    }
//
//    public void initialize() {
//        this.setImage(Assets.bombImage);
//        setWidth(30) ;
//        setHeight(90) ;
//        setV(7);
//        setpower(1);
//        setTempUp(5);
//        setThrowPermission(true);
//        this.setDimensions();
//    }

    MultiArrows multiArrows ;

    public MultiBeam(int x0, int y0) {
        super();
        this.x0 = x0 ;
        this.y0 = y0 ;
        multiArrows = new MultiArrows(x0,y0) ;

        this.setThrowPermission(true);
    }


    @Override
    public void moveBeam() {
        multiArrows.moveBeam();
    }


    @Override
    public void draw(Graphics g) {
        for (int i=0 ; i<5 ; i++){
            Beam beam = multiArrows.getBeams()[i] ;
            g.drawImage(beam.getImage(),beam.getX(),beam.getY(),beam.getWidth(),beam.getHeight(),null) ;
        }
    }

    //getters


    public MultiArrows getMultiArrows() {
        return multiArrows;
    }
}
