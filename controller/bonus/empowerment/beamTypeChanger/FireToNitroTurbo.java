package controller.bonus.empowerment.beamTypeChanger;

import controller.bonus.empowerment.Turbo;
import view.imaging.ImageLoader;

public class FireToNitroTurbo extends Turbo {

    static String diamondAddress = "pictures/bonus/diamond.png" ;

    public FireToNitroTurbo(int x, int y) {
        super(x, y);
        initialize();
    }


    public void initialize(){
        setAddress(diamondAddress);
        setImage(ImageLoader.Load(getAddress()));
        setWidth(70);
        setHeight(70);
        setInScreen(true);
        handleJob();
    }

    @Override
    public void handleJob() {

    }

}
