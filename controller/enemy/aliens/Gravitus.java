package controller.enemy.aliens;

public class Gravitus extends Alien {

    public Gravitus() {
        initialize();
    }


    public Gravitus(int x, int y) {
        super(x,y) ;
        initialize();
    }


    public Gravitus(int xc , int yc , int r, double teta){
        super(xc , yc , r , teta) ;
        initialize();
    }

    public void initialize(){
        setName(AlienName.GRAVITUS) ;
        setImages("pictures//aliens//gravitus//gravitus.png" );
        setHeight(100);
        setWidth(200);
    }

}
