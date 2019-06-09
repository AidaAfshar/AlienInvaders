package controller.enemy.aliens;

import view.imaging.Assets;

public class Ophelia extends Alien{

    public Ophelia() {
        initialize();
    }


    public Ophelia(int x, int y) {
        super(x,y) ;
        initialize();
    }


    public Ophelia(int xc , int yc , int r, double teta){
        super(xc , yc , r , teta) ;
        initialize();
    }

    public void initialize(){
        setName(AlienName.OPHELIA) ;
        setImages(Assets.ophelia1Image,Assets.ophelia2Image );
        setHeight(75);
        setWidth(95);
    }


}
