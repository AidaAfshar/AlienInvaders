package controller.main.Connection.client;

import controller.player.Player;
import view.screen.ClientPanel;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Thread{

    Socket socket ;

    String IP ;
    int port ;

    Player clientPlayer;
    ClientPanel clientPanel ;

    ArrayList<Player> otherPlayers ;

    ConnectionServiceForClient connector;
    UpdateServiceForClient updater;

    public Client(String IP , int port , Player player , ClientPanel clientPanel){
        this.IP = IP ;
        this.port = port ;
        this.clientPlayer = player ;
        this.clientPanel = clientPanel ;
    }


    @Override
    public void run() {
        super.run();
        try {

            socket = new Socket(IP , port) ;

            connector = new ConnectionServiceForClient(
                    socket.getOutputStream(),
                    socket.getInputStream(),
                    clientPlayer,
                    clientPanel);




        } catch (Exception e) {
            e.printStackTrace();
        }

    }





    //getters & setters:

    public Player getClientPlayer() {
        return clientPlayer;
    }

    public void setClientPlayer(Player clientPlayer) {
        this.clientPlayer = clientPlayer;
    }

    public ArrayList<Player> getOtherPlayers() {
        return connector.otherPlayers;
    }

    public void setOtherPlayers(ArrayList<Player> otherPlayers) {
        this.otherPlayers = otherPlayers;
    }
}
