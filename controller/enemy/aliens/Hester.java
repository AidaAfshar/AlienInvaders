package controller.enemy.aliens;

import view.imaging.Assets;

public class Hester extends Alien {


    public Hester() {
        initialize();
    }

    public Hester(int x, int y) {
        super(x,y) ;
        initialize();
    }


    public Hester(int xc , int yc , int r, double teta){
        super(xc , yc , r , teta) ;
        initialize();
    }

    public void initialize(){
        setName(AlienName.HESTER) ;
        setProbability(2000);
        setPower(1);
        setResistance(1);
        setImages(Assets.hester1Image,Assets.hester2Image );
        setHeight(90);
        setWidth(80);
    }




}
