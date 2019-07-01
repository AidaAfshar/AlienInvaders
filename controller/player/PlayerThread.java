package controller.player;

public class PlayerThread extends Thread {

    Player player ;

    public PlayerThread(Player player){
        this.player = player ;
    }


    @Override
    public void run() {
        super.run();

    }


}
