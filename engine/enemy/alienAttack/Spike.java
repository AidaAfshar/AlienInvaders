package engine.enemy.alienAttack;

import fronted.imaging.Image;
import fronted.imaging.ImageLoader;

import java.awt.*;

public class Spike extends Image {

    String spikeAddress = "pictures/aliens/spikes/spike.png" ;


    int x ;
    int y ;
    int width = 50 ;
    int height = 50 ;
    boolean collided = false ;
    boolean release = false ;


    public Spike(int x , int y){
        this.x = x ;
        this.y = y ;
        this.release = true ;
        initialize();
    }

    public void initialize() {
        this.setAddress(spikeAddress);
        this.setImage(ImageLoader.Load(this.getAddress()));
    }

    public void move(){
        this.y+=5 ;
    }

    public void render(Graphics g){
        g.drawImage(this.getImage(),this.x,this.y,this.width,this.height,null);
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
