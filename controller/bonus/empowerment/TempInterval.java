package controller.bonus.empowerment;

import controller.ship.SpaceShip;
import view.imaging.Assets;

public class TempInterval extends Turbo {


    public TempInterval(int x, int y) {
        super(x, y);
        initialize();
    }


    public void initialize(){
        setType(TurboType.TEMP_INTERVAL);
        setImage(Assets.redCubeImage);
        setWidth(60);
        setHeight(60);
        setInScreen(true);
    }

    @Override
    public void handleJob(SpaceShip ship) {
        ship.setMaximumTemp(ship.getMaximumTemp()+5);
    }


}
