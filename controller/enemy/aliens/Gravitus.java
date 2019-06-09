package controller.enemy.aliens;

import controller.attackTools.BeamType;
import view.imaging.Assets;

import java.awt.*;

public class Gravitus extends Alien {

    public Gravitus() {
        initialize();
    }


    public Gravitus(int x, int y) {
        super(x,y) ;
        initialize();
    }


    public void initialize(){
        setName(AlienName.GRAVITUS) ;
        setImage(Assets.gravitusImage );
        setAlive(true);
        setHeight(540);
        setWidth(540);
    }

    @Override
    public void draw(Graphics g){
        g.drawImage(getImage(), x, y, width, height, null);

    }

    @Override
    public void gotHit(int x , int y , BeamType type){
        setAlive(false);
    }

}
