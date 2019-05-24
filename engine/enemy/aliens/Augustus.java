package engine.enemy.aliens;

import java.awt.*;

public class Augustus extends Alien{

    public Augustus() {
        initialize();
    }


    public Augustus(int x, int y) {
        this.x = x ;
        this.y = y ;
        initialize();
    }

    public void initialize(){
        setName(AlienName.AUGUSTUS) ;
        setImages("pictures/aliens/augustus/1.png" ,"pictures/aliens/augustus/2.png","pictures/aliens/augustus/3.png" );
        setHeight(95);
        setWidth(90);
    }


}
