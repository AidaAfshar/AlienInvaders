package controller.bonus;

import view.imaging.Assets;

public class Coin extends Bonus {

    boolean showPermition = true ;

    public Coin(int x, int y) {
        super(x, y);
        initialize();
    }


    public void initialize(){
        setImage(Assets.coinImage);
        setWidth(40);
        setHeight(40);
        setInScreen(true);
    }

    public boolean getShowPermition() {
        return showPermition;
    }

    public void setShowPermition(boolean showPermition) {
        this.showPermition=showPermition;
    }
}
