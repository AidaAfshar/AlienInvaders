package engine.enemy.aliens;

import java.awt.Graphics;
import java.util.ArrayList;

import fronted.imaging.Image;
import fronted.imaging.ImageLoader;


public class Alien {

    //attributes:

    AlienName name ;
    int width ;
    int height ;
    int x ; int y ;
    int r ; double teta ;
    int vx , vy ;
    boolean alive = true ;
    ArrayList<Image> image ;
    int j=0 ;

    //methods:

    public Alien(){
        image = new ArrayList<>() ;
    }

    public void setImages(String... address) {
        for(int i=0 ; i<address.length ; i++) {
            image.add(new Image(address[i]));
            image.get(i).setImage(ImageLoader.Load(image.get(i).getAddress()));
        }
    }

    public void setV(int xc , int yc){
        if(this.getX()>xc && this.getY()<yc){
            this.setVx(-1);
            this.setVy(-1);
        }else if(this.getX()<xc && this.getY()<yc){
            this.setVx(-1);
            this.setVy(+1);
        }else if(this.getX()<xc && this.getY()>yc){
            this.setVx(+1);
            this.setVy(+1);
        }else if(this.getX()>xc && this.getY()>yc){
            this.setVx(+1);
            this.setVy(-1);
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

    public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
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

             if (i % 25 < 12)
                j = 0;
            else
                j = 1;
            i++;
    }


}
