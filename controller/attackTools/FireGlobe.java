package controller.attackTools;

import view.imaging.Assets;

public class FireGlobe extends Beam {



    public FireGlobe(int x0, int y0) {
        super();
        this.x0 = x0 ;
        this.y0 = y0 ;
        initialize();
    }

    public void initialize() {
        this.setImage(Assets.fireGlobeImage);
        setWidth(80) ;
        setHeight(120) ;
        setV(7);
        setPower(2);
        setTempUp(10);
        setThrowPermission(true);
        this.setDimensions();
    }


}
