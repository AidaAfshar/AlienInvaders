package controller.bonus.empowerment;

import view.imaging.ImageLoader;

public class TempInterval extends Turbo {

    static String redCubeAddress = "pictures/bonus/redCube.png" ;

    public TempInterval(int x, int y) {
        super(x, y);
        initialize();
    }


    public void initialize(){
        setAddress(redCubeAddress);
        setImage(ImageLoader.Load(getAddress()));
        setWidth(60);
        setHeight(60);
        setInScreen(true);
        handleJob();
    }

    @Override
    public void handleJob() {

    }


}
