package controller.enemy.aliens;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import controller.attackTools.BeamType;
import controller.enemy.alienGroups.Group;
import view.imaging.Image;
import view.imaging.ImageLoader;
import view.utilities.Dim;


public class Alien extends Image{


    public static AlienName[] alien = {AlienName.HESTER , AlienName.OPHELIA, AlienName.AUGUSTUS ,AlienName.BLOODREX};

    AlienName name ;
    ArrayList<BufferedImage> image = new ArrayList<>() ;
    int width ;
    int height ;
    int x , y ;
    int vx , vy ;
    boolean alive = true ;

    Group group ;

    public Alien(){

    }

    public Alien(int x, int y) {
        this.x = x ;
        this.y = y ;
    }

    // POLAR COORDINATES :

    int r ; double teta ;

    public Alien(int xc , int yc , int r, double teta){
        this.r = r ;
        this.teta = teta ;
        setPolarCoordinates(xc ,yc ,r ,teta);

    }


    public void setPolarCoordinates(int xc , int yc , int r , double teta){
        this.r = r ;
        this.teta = teta ;
        this.x = (int)(xc + ((double)r)*Math.cos(teta)) ;
        this.y = (int)(yc + ((double)r)*Math.sin(teta)) ;
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

    //RANDOM COORDINATES:

    Random random = new Random();
    int nextX ,nextY;

    public void produceCoordinate(){
        nextX = random.nextInt(Dim.MAX_X);
        nextY = random.nextInt(Dim.MAX_Y);
    }

    public int getNextX() {
        return nextX;
    }

    public int getNextY() {
        return nextY;
    }

    public void setNextX(int nextX) {
        this.nextX = nextX;
    }

    public void setNextY(int nextY) {
        this.nextY = nextY;
    }

    public boolean reachedNextX(){
        if(x<nextX+20 && x>nextX-20)
            return true ;

        return false ;
    }

    public boolean reachedNextY(){
        if(y<nextY+20 && y>nextY-20)
            return true ;

        return false ;
    }

    public boolean reachedNextDestination(){
        if(this.reachedNextX() && this.reachedNextY())
            return true ;
        else
            return false ;
    }


    //----


    public void setImages(BufferedImage... images) {
        for(int i=0 ; i<images.length ; i++)
            image.add(images[i]) ;

    }


    int i=0 ;
    int j=0 ;

    public void draw(Graphics g) {
            g.drawImage(image.get(j), x, y, width, height, null);

             if (i % 25 < 12)
                j = 0;
            else
                j = 1;
            i++;
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

    //getters & setters :

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

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public double getTeta() {
        return teta;
    }

    public void setTeta(double teta) {
        this.teta = teta;
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

    public void setAlive(boolean b){
        alive = b ;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void gotHit(int x , int y , BeamType type) {
        setAlive(false);
        group.releaseBonus(x,y,type) ;
    }

    public boolean isInside(){
        if(x<0 || x> Dim.MAX_X) return false ;
        if(y<0 || y> Dim.MAX_Y) return false ;

        return true ;
    }


}
