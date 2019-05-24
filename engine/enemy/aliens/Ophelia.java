package engine.enemy.aliens;

public class Ophelia extends Alien{

    public Ophelia() {
        initialize();
    }


    public Ophelia(int x, int y) {
        this.x = x ;
        this.y = y ;
        initialize();
    }

    public void initialize(){
        setName(AlienName.OPHELIA) ;
        setImages("pictures/aliens/ophelia/1.png" ,"pictures/aliens/ophelia/2.png" );
        setHeight(75);
        setWidth(95);
    }


}
