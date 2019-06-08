package controller.bonus.empowerment.beamTypeChanger;

import controller.bonus.empowerment.Turbo;
import view.imaging.ImageLoader;

public class NitroToFlame extends Turbo {

    static String purpleCubeAddress = "pictures/bonus/purpleCube.png" ;

    public NitroToFlame(int x, int y) {
        super(x, y);
        initialize();
    }

    public void initialize(){
        setAddress(purpleCubeAddress);
        setImage(ImageLoader.Load(getAddress()));
        setWidth(60);
        setHeight(60);
        setInScreen(true);
        handleJob();
    }

    @Override
    public void handleJob() {

    }


}
