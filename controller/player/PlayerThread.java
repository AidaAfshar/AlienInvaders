package controller.player;

import controller.player.playerExtentions.Player;

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
