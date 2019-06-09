package controller.enemy.alienAttack;

public class FinalSpike extends Spike {

    int vx ,vy ;

    public FinalSpike(int x, int y, int vx, int vy) {
        super(x, y);
        this.vx = vx;
        this.vy = vy;
    }

    @Override
    public void move() {
        setX(getX()+vx);
        setY(getY()+vy);
    }


}
