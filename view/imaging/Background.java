package view.imaging;

import java.awt.Graphics;


public class Background extends Image{

    public Background() {
        super();
    }

    public void draw(Graphics g ) {
        g.drawImage(this.getImage(),0,0,null);
    }

}
