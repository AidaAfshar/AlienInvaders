package view.imaging;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Image {

    //attributes:

    transient BufferedImage image ;
    String Address ;

    protected int x , y;
    int width , height ;


    //methods:

    public Image() {

    }

    public Image(String imageAddress) {
        this.Address = imageAddress ;
        this.image = ImageLoader.load(Address);
    }

    public BufferedImage getImage() {
        return image ;
    }

    public String getAddress() {
        return Address ;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void draw(Graphics g){
        g.drawImage(getImage(),x , y , width,height,null);
    }


    //getters & setters:


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
}
