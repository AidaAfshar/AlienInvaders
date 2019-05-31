package view.imaging;

import java.awt.image.BufferedImage;

public class Image {

    //attributes:

    transient BufferedImage image ;
    String Address ;

    //methods:

    public Image() {

    }

    public Image(String imageAddress) {
        this.Address = imageAddress ;
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



}
