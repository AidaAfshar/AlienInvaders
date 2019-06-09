package controller.bonus.empowerment.beamTypeChanger;

import controller.attackTools.BeamType;
import controller.bonus.empowerment.Turbo;
import controller.bonus.empowerment.TurboType;
import controller.ship.SpaceShip;
import view.imaging.ImageLoader;

public class FlameTurbo extends Turbo {

    static String purpleCubeAddress = "pictures/bonus/purpleCube.png" ;

    public FlameTurbo(int x, int y) {
        super(x, y);
        initialize();
    }

    public void initialize(){
        setType(TurboType.FLAME_TURBO);
        setAddress(purpleCubeAddress);
        setImage(ImageLoader.Load(getAddress()));
        setWidth(60);
        setHeight(60);
        setInScreen(true);
    }

    @Override
    public void handleJob(SpaceShip ship) {
        ship.setBeamType(BeamType.FLAMEBALL);
    }


}
