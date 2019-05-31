package controller.enemy.aliens;

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
        setImages("pictures/aliens/ophelia/1.png" ,"pictures/aliens/ophelia/2.png" );
        setHeight(75);
        setWidth(95);
    }


}
