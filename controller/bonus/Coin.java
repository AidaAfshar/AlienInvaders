package controller.bonus;

import view.imaging.Assets;

public class Coin extends Bonus {


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


}
