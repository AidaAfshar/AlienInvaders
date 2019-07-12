package controller.main.Connection;

import controller.player.Player;
import model.dataManagement.DataManager;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ConnectionService extends Thread{

    PrintWriter printer ;
    Scanner scanner ;

    ArrayList<Player> otherPlayers ;
    Player newPlayer ;

    public ConnectionService(OutputStream outputStream,InputStream inputStream ,ArrayList<Player> otherPlayers){
        printer = new PrintWriter(outputStream) ;
        scanner = new Scanner(inputStream) ;
        this.otherPlayers = otherPlayers ;
    }


    @Override
    public void run() {
        super.run();

        sendOtherPlayersToClient();
        loadNewPlayer();



    }


    public void sendOtherPlayersToClient(){
        for (Player player : otherPlayers) {
            player.save();
            printer.println(player);
            printer.flush();
        }
    }

    public void loadNewPlayer(){
        newPlayer = DataManager.load(scanner);
    }

    public void updatePlayersList(Player player){
        otherPlayers.add(player) ;
        sendOtherPlayersToClient();
    }


    //getters & setters:


    public Player getNewPlayer() {
        return newPlayer;
    }

    public void setNewPlayer(Player newPlayer) {
        this.newPlayer = newPlayer;
    }
}
