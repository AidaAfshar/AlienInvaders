package controller.bonus;

import view.imaging.ImageLoader;

public class Coin extends Bonus {

    static String coinAddress = "pictures/bonus/coin.png" ;

    public Coin(int x, int y) {
        super(x, y);
        initialize();
    }


    public void initialize(){
        setAddress(coinAddress);
        setImage(ImageLoader.Load(getAddress()));
        setWidth(40);
        setHeight(40);
        setInScreen(true);
    }


}
