package controller.bonus.empowerment;

import controller.attackTools.BeamType;
import controller.bonus.Bonus;
import controller.bonus.empowerment.beamTypeChanger.FireToNitroTurbo;
import controller.bonus.empowerment.beamTypeChanger.FlameToFireTurbo;
import controller.bonus.empowerment.beamTypeChanger.NitroToFlame;

public abstract class Turbo extends Bonus {



    public Turbo(int x, int y) {
        super(x, y);
    }


    public static Turbo diagnoseTurbo(int x , int y , BeamType type){
        if(type.equals(BeamType.FLAMEBALL)) {
    //        System.out.println("FF");
            return new FlameToFireTurbo(x, y);
        }
        if(type.equals(BeamType.FIREGLOBE)) {
    //        System.out.println("FN");
            return new FireToNitroTurbo(x, y);
        }
        if(type.equals(BeamType.NITROGLOBE)) {
    //        System.out.println("NF");
            return new NitroToFlame(x, y);
        }
        return null ;
    }


}
