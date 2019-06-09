package controller.bonus.empowerment;

import controller.ship.SpaceShip;
import view.imaging.ImageLoader;

public class TempInterval extends Turbo {

    static String redCubeAddress = "pictures/bonus/redCube.png" ;

    public TempInterval(int x, int y) {
        super(x, y);
        initialize();
    }


    public void initialize(){
        setType(TurboType.TEMP_INTERVAL);
        setAddress(redCubeAddress);
        setImage(ImageLoader.Load(getAddress()));
        setWidth(60);
        setHeight(60);
        setInScreen(true);
    }

    @Override
    public void handleJob(SpaceShip ship) {
        ship.setMaximumTemp(ship.getMaximumTemp()+5);
    }


}
