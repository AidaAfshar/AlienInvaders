package engine.attackTools;

import java.awt.Graphics;

import fronted.imaging.Image;
import fronted.screen.GamePanel;


public class Beam extends Image{

    int x ;
    int y ;
    int height ;
    int width ;
    int halfHeight ;
    int halfWidth ;
    public boolean throwPermission = false ;

    public Beam(String address) {
        super(address);
        initialize();

    }

    public Beam() {
        initialize();
    }

    public void initialize() {
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

//		this.x = GamePanel.ml.x - this.halfWidth ;
//		this.y = GamePanel.ml.y - this.halfHeight ;

        this.x = GamePanel.ml.x - this.halfWidth ;
        this.y = GamePanel.ml.y - this.halfHeight-40 ;

    }


    public void draw(Graphics g) {
        g.drawImage(this.getImage(),this.x,this.y,this.width,this.height, null);
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

}
