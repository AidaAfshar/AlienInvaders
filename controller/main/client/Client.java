package controller.main.client;

import controller.player.Player;
import model.dataManagement.DataManager;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Thread {

    Socket socket ;

    String IP ;
    int port ;
    Player clientPlayer;

    ArrayList<Player> otherPlayers ;

    PrintStream printer ;
    Scanner scanner ;

    public Client(String IP , int port , Player player){
        this.IP = IP ;
        this.port = port ;
        this.clientPlayer = player ;
    }


    @Override
    public void run() {
        super.run();
        try {

            socket = new Socket(IP , port) ;
            scanner = new Scanner(socket.getInputStream()) ;
            printer = new PrintStream(socket.getOutputStream()) ;

         //   receiveOtherPlayersFromServer() ;
            sendClientPlayerToServer() ;


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void sendClientPlayerToServer(){
        clientPlayer.save();
        printer.println(clientPlayer);
        printer.flush();
    }

    private void receiveOtherPlayersFromServer(){
        otherPlayers = new ArrayList<>() ;
        while (scanner.hasNextLine()){
            otherPlayers.add(DataManager.load(scanner)) ;
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
        return otherPlayers;
    }

    public void setOtherPlayers(ArrayList<Player> otherPlayers) {
        this.otherPlayers = otherPlayers;
    }
}
