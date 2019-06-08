package controller.bonus.empowerment.beamTypeChanger;

import controller.bonus.empowerment.Turbo;
import view.imaging.ImageLoader;

public class FlameToFireTurbo extends Turbo {

    static String starAddress = "pictures/bonus/star.png" ;

    public FlameToFireTurbo(int x, int y) {
        super(x, y);
        initialize();
    }

    public void initialize(){
        setAddress(starAddress);
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
