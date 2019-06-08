package controller.attackTools;

import view.imaging.ImageLoader;

public class FlameBall extends Beam {

    static String flameBallAddress = "pictures/beams/flameball.png" ;


    public FlameBall(int x0, int y0) {
        super(flameBallAddress);
        this.x0 = x0 ;
        this.y0 = y0 ;
        initialize();
    }

    public void initialize() {
        this.setImage(ImageLoader.Load(this.getAddress()));
        setWidth(30) ;
        setHeight(90) ;
        setV(7);
        setPower(1);
        setTempUp(5);
        setThrowPermission(true);
        this.setDimensions();
    }



}
