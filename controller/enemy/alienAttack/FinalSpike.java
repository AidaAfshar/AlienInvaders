package controller.enemy.alienAttack;

import view.imaging.Assets;

public class FinalSpike extends Spike {

    int vx ,vy ;

    public FinalSpike(int x, int y, int vx, int vy) {
        super(x, y);
        this.vx = vx;
        this.vy = vy;
        initialize();
    }

    public void initialize() {
        this.setImage(Assets.spikeImage);
        setRelease(true);
        setHeight(50);
        setWidth(50);
    }


    @Override
    public void move() {
        setX(getX()+vx);
        setY(getY()+vy);
    }


}
