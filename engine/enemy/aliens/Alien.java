package engine.enemy.aliens;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import fronted.imaging.Image;
import fronted.imaging.ImageLoader;


public class Alien {

    int width ;
    int height ;
    int x ;
    int y ;
    boolean alive = true ;
    ArrayList<Image> image = new ArrayList<Image>() ;
    AlienName name ;

    public int j=0 ;




    public void setImages(String... address) {
        for(int i=0 ; i<address.length ; i++) {
            image.add(new Image(address[i]));
            image.get(i).setImage(ImageLoader.Load(image.get(i).getAddress()));
        }

    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setName(AlienName name) {
        this.name = name;
    }

    public AlienName getName() {
        return name;
    }

    public boolean isAlive() {
        return alive;
    }


    public void gotHit() {
        alive = false ;
    }

    public static  Alien diagnoseType(AlienName alienName) {
        if(alienName.equals(AlienName.HESTER))
            return new Hester();
        else if(alienName.equals(AlienName.OPHELIA))
            return new Ophelia();
        else if(alienName.equals(AlienName.AUGUSTUS))
            return new Augustus();
        else if(alienName.equals(AlienName.BLOODREX))
            return new Bloodrex();

        return null ;

    }



    int i=0 ;

    public void draw(Graphics g) {
        g.drawImage(image.get(j).getImage(), x, y, width, height, null);
        if(i%25<12)
            j = 0 ;
        else
            j = 1 ;
        i++ ;
    }

}
