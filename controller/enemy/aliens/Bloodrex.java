package controller.enemy.aliens;

import view.imaging.Assets;

public class Bloodrex extends Alien{

    public Bloodrex() {
        initialize();
    }


    public Bloodrex(int x, int y) {
        super(x,y) ;
        initialize();
    }


    public Bloodrex(int xc , int yc , int r, double teta){
        super(xc , yc , r , teta) ;
        initialize();
    }


    public void initialize(){
        setName(AlienName.BLOODREX) ;
        setImages(Assets.bloodrex1Image ,Assets.bloodrex2Image );
        setProbablity(500);
        setPower(3);
        setHeight(90);
        setWidth(85);
    }


}
