package engine.enemy.aliens;

import java.awt.Graphics;

import fronted.imaging.ImageLoader;

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
        setImages("pictures/aliens/hester/1.png" ,"pictures/aliens/hester/2.png" );
        setHeight(90);
        setWidth(80);
    }




}
