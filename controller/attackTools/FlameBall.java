package controller.attackTools;

import view.imaging.ImageLoader;

public class FlameBall extends Beam {

    static String flameBallAddress = "pictures/beams/flameball.png" ;

    final int x0 ;
    final int y0 ;
    boolean throwPermission ;

    public FlameBall(int x0, int y0) {
        super(flameBallAddress);
        this.x0 = x0 ;
        this.y0 = y0 ;
        initialize();
    }

    public void initialize() {
        this.setImage(ImageLoader.Load(this.getAddress()));
        throwPermission = true ;
        this.width = 25 ;
        this.height = 80 ;
        this.setDimensions();
    }


    public void moveFlameBall() {
        this.y -= 7 ;
        if(this.y<20) {
            this.throwPermission=false ;
        }
    }

    public boolean getThrowPermission() {
        return throwPermission;
    }

    public void setThrowPermission(boolean throwPermission) {
        this.throwPermission = throwPermission;
    }

}
