package controller.attackTools;

import view.imaging.Assets;

public class FlameBall extends Beam {


    public FlameBall(int x0, int y0) {
        super();
        this.x0 = x0 ;
        this.y0 = y0 ;
        initialize();
    }

    public void initialize() {
        this.setImage(Assets.flameBallImage);
        setWidth(30) ;
        setHeight(90) ;
        setV(7);
        setPower(1);
        setTempUp(5);
        setThrowPermission(true);
        this.setDimensions();
    }



}
