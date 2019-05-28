package engine.enemy.aliens;

public class Bloodrex extends Alien{

    public Bloodrex() {
        initialize();
    }


    public Bloodrex(int x, int y) {
        this.x = x ;
        this.y = y ;
        initialize();
    }


    public Bloodrex(int r, double teta){
        this.r = r ;
        this.teta = teta ;
        initialize();
    }

    public void initialize(){
        setName(AlienName.BLOODREX) ;
        setImages("pictures/aliens/bloodrex/1.png" ,"pictures/aliens/bloodrex/2.png" );
        setHeight(90);
        setWidth(85);
    }


}
