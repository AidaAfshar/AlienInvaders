package controller.attackTools;

import view.imaging.ImageLoader;

public class FireGlobe extends Beam {

    static String fireGlobeAddress = "pictures/beams/fire.png" ;


    public FireGlobe(int x0, int y0) {
        super(fireGlobeAddress);
        this.x0 = x0 ;
        this.y0 = y0 ;
        initialize();
    }

    public void initialize() {
        this.setImage(ImageLoader.Load(this.getAddress()));
        setWidth(120) ;
        setHeight(120) ;
        setV(7);
        setPower(2);
        setTempUp(10);
        setThrowPermission(true);
        this.setDimensions();
    }


}
