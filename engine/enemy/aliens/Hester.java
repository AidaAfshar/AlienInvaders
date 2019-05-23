package engine.enemy.aliens;

import java.awt.Graphics;

import fronted.imaging.ImageLoader;

public class Hester extends Alien {


    public Hester() {
        initialize();
    }

    public Hester(int x, int y) {
        this.x = x ;
        this.y = y ;
        initialize();
    }

    public void initialize(){
        setName(AlienName.HESTER) ;
        setImages("pictures/aliens/hester/1.png" ,"pictures/aliens/hester/2.png" );
        setHeight(90);
        setWidth(80);
    }

    int i=0 ;

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if(i%25<12)
            j = 0 ;
        else
            j = 1 ;
        i++ ;
    }



}
