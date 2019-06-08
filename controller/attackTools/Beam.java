package controller.attackTools;

import java.awt.Graphics;

import view.imaging.Image;
import view.screen.GamePanel;


public abstract class Beam extends Image{

    int power ;
    int tempUp ;
    int v ;
    int x0 , y0;
    int x , y ;
    int width ,height ;
    int halfWidth ,halfHeight ;
    boolean throwPermission = false ;


    public Beam(String address) {
        super(address);
        initialize();

    }


    public void initialize() {

    }


    public void moveBeam(){
        y-= v ;
        if(y<-20)
            setThrowPermission(false);
    }

    public void setDimensions(int width ,int height) {
        this.width = width ;
        this.height = height ;

        this.halfWidth = (int) width/2 ;
        this.halfHeight = (int) height/2 ;

    }

    public void setDimensions() {
        this.halfWidth = (int) width/2 ;
        this.halfHeight = (int) height/2 ;

        this.x = x0 - halfWidth ;
        this.y = y0 - halfHeight - 35 ;

    }

    public void draw(Graphics g) {
        g.drawImage(this.getImage(),this.x,this.y,this.width,this.height, null);
    }


    //getters & setters :

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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean getThrowPermission(){
        return throwPermission ;
    }

    public void setThrowPermission(boolean b){
        throwPermission = b ;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getTempUp() {
        return tempUp;
    }

    public void setTempUp(int tempUp) {
        this.tempUp = tempUp;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }
}
