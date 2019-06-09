package controller.bonus.empowerment.beamTypeChanger;

import controller.attackTools.BeamType;
import controller.bonus.empowerment.Turbo;
import controller.bonus.empowerment.TurboType;
import controller.ship.SpaceShip;
import view.imaging.ImageLoader;

public class FireTurbo extends Turbo {

    static String starAddress = "pictures/bonus/star.png" ;

    public FireTurbo(int x, int y) {
        super(x, y);
        initialize();
    }

    public void initialize(){
        setType(TurboType.FIRE_TURBO);
        setAddress(starAddress);
        setImage(ImageLoader.Load(getAddress()));
        setWidth(60);
        setHeight(60);
        setInScreen(true);

    }

    @Override
    public void handleJob(SpaceShip ship) {
        ship.setBeamType(BeamType.FIREGLOBE);
    }


}
