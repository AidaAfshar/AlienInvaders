package controller.bonus.empowerment.beamTypeChanger;

import controller.attackTools.BeamType;
import controller.bonus.empowerment.Turbo;
import controller.bonus.empowerment.TurboType;
import controller.ship.SpaceShip;
import view.imaging.ImageLoader;

public class NitroTurbo extends Turbo {

    static String diamondAddress = "pictures/bonus/diamond.png" ;

    public NitroTurbo(int x, int y) {
        super(x, y);
        initialize();
    }


    public void initialize(){
        setType(TurboType.NITRO_TURBO);
        setAddress(diamondAddress);
        setImage(ImageLoader.Load(getAddress()));
        setWidth(70);
        setHeight(70);
        setInScreen(true);
    }

    @Override
    public void handleJob(SpaceShip ship) {
        ship.setBeamType(BeamType.NITROGLOBE);
    }

}
