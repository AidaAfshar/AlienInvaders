package controller.bonus.empowerment;

import controller.attackTools.BeamType;
import controller.bonus.Bonus;
import controller.bonus.empowerment.beamTypeChanger.NitroTurbo;
import controller.bonus.empowerment.beamTypeChanger.FireTurbo;
import controller.bonus.empowerment.beamTypeChanger.FlameTurbo;
import controller.ship.SpaceShip;

public abstract class Turbo extends Bonus {

    TurboType type ;

    public Turbo(int x, int y) {
        super(x, y);
    }


    public abstract void handleJob(SpaceShip ship) ;


    public static Turbo diagnoseTurbo(int x , int y , BeamType type){
        if(type.equals(BeamType.FLAMEBALL)) {
    //        System.out.println("FF");
            return new FireTurbo(x, y);
        }
        if(type.equals(BeamType.FIREGLOBE)) {
    //        System.out.println("FN");
            return new NitroTurbo(x, y);
        }
        if(type.equals(BeamType.NITROGLOBE)) {
    //        System.out.println("NF");
            return new FlameTurbo(x, y);
        }
        return null ;
    }

    //getters & setters:


    public TurboType getType() {
        return type;
    }

    public void setType(TurboType type) {
        this.type = type;
    }
}
