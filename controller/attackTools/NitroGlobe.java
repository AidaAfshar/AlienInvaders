package controller.attackTools;

import view.imaging.Assets;

public class NitroGlobe extends Beam {


    public NitroGlobe(int x0, int y0) {
        super();
        this.x0 = x0 ;
        this.y0 = y0 ;
        initialize();
    }

    public void initialize() {
        this.setImage(Assets.nitroGlobeImage);
        setWidth(90) ;
        setHeight(130) ;
        setV(7);
        setPower(3);
        setTempUp(15);
        setThrowPermission(true);
        this.setDimensions();
    }

}
