package controller.enemy.alienAttack;

import view.imaging.Assets;
import view.imaging.Image;
import view.imaging.ImageLoader;

import java.awt.*;

public class Spike extends Image {


    boolean collided = false ;
    boolean release = false ;


    public Spike(int x , int y){
        setX(x);
        setY(y);
        initialize();
    }

    public void initialize() {
        this.setImage(Assets.spikeImage);
        setRelease(true);
        setHeight(50);
        setWidth(50);
    }

    public void move(){
        setY(getY()+5);
    }


    // getters & setters :


    public boolean isCollided() {
        return collided;
    }

    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }


}
