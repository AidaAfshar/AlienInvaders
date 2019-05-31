package controller.enemy.aliens;

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
        setImages("pictures/aliens/bloodrex/1.png" ,"pictures/aliens/bloodrex/2.png" );
        setHeight(90);
        setWidth(85);
    }


}
