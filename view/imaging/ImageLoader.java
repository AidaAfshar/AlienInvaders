package view.imaging;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

    //methods:

    public static BufferedImage load(String imageAddress) {
        BufferedImage image = null ;
        try {
            image  = ImageIO.read(new File(imageAddress));
        } catch (IOException e) {
            e.printStackTrace() ;
        }
        return image ;
    }



}
