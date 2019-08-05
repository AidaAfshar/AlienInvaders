package controller.enemy.aliens;


import view.imaging.Assets;

public class Augustus extends Alien{

    public Augustus() {
        initialize();
    }


    public Augustus(int x, int y) {
        super(x,y) ;
        initialize();
    }


    public Augustus(int xc , int yc , int r, double teta){
        super(xc , yc , r , teta) ;
        initialize();
    }

    public void initialize(){
        setName(AlienName.AUGUSTUS) ;
        setProbability(1000);
        setPower(3);
        setResistance(3);
        setImages(Assets.augustus1Image ,Assets.augustus2Image);
        setHeight(95);
        setWidth(90);
    }


}
