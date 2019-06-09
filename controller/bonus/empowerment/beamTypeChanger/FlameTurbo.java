package controller.bonus.empowerment.beamTypeChanger;

import controller.attackTools.BeamType;
import controller.bonus.empowerment.Turbo;
import controller.bonus.empowerment.TurboType;
import controller.ship.SpaceShip;
import view.imaging.Assets;

public class FlameTurbo extends Turbo {


    public FlameTurbo(int x, int y) {
        super(x, y);
        initialize();
    }

    public void initialize(){
        setType(TurboType.FLAME_TURBO);
        setImage(Assets.purpleCubeImage);
        setWidth(60);
        setHeight(60);
        setInScreen(true);
    }

    @Override
    public void handleJob(SpaceShip ship) {
        ship.setBeamType(BeamType.FLAMEBALL);
    }


}
