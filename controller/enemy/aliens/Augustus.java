package controller.enemy.aliens;


public class Augustus extends Alien{

    public Augustus() {
        initialize();
    }


    public Augustus(int x, int y) {
        super(x,y) ;
        initialize();
    }


    public Augustus(int xc , int yc , int r, double teta){
        super(xc , yc , r , teta) ;
        initialize();
    }

    public void initialize(){
        setName(AlienName.AUGUSTUS) ;
        setImages("pictures/aliens/augustus/1.png" ,"pictures/aliens/augustus/2.png","pictures/aliens/augustus/3.png" );
        setHeight(95);
        setWidth(90);
    }


}
