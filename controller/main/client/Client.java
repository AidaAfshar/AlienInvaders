package controller.main.client;

import controller.main.server.Server;
import controller.player.Player;
import model.dataManagement.DataManager;
import view.screen.ClientPanel;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Thread {

    Socket socket ;

    String IP ;
    int port ;
    Player clientPlayer;


    ArrayList<Player> otherPlayers ;

    PrintWriter printer ;
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
            printer = new PrintWriter(socket.getOutputStream()) ;
            scanner = new Scanner(socket.getInputStream()) ;


            sendClientPlayerToServer() ;
            receiveOtherPlayersFromServer() ;


            //why is it working well without synchronization? :)) :/
            //its not working -_-

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void sendClientPlayerToServer(){
        clientPlayer.save();
        printer.println(clientPlayer);
        printer.flush();
    }


    public void receiveOtherPlayersFromServer() throws IOException {
        otherPlayers = new ArrayList<>();
        while (scanner.hasNextLine()){
            Player player = DataManager.load(scanner.nextLine()) ;
            otherPlayers.add(player) ;
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
