package controller.main.Connection.client;

import controller.player.Player;
import model.dataManagement.DataManager;
import view.screen.ClientPanel2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Thread {

    Socket socket ;

    String IP ;
    int port ;

    Player clientPlayer;
    ClientPanel2 clientPanel ;

    ArrayList<Player> otherPlayers ;

    PrintWriter printer ;
    Scanner scanner ;
    public Client(String IP , int port , Player player , ClientPanel2 clientPanel){
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
            printer = new PrintWriter(socket.getOutputStream()) ;
            scanner = new Scanner(socket.getInputStream()) ;

            prepareTimer();
            timer.start();

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


    public void receiveOtherPlayersFromServer() {
        otherPlayers = new ArrayList<>();
        while (scanner.hasNextLine()){
            Player player = DataManager.load(scanner.nextLine()) ;
            otherPlayers.add(player) ;
        }
    }


    Timer timer ;
    void prepareTimer(){
         timer = new Timer(3000, new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 receiveOtherPlayersFromServer();
                 clientPanel.setOtherPlayers(otherPlayers);
                 clientPanel.setPlayersCount(otherPlayers.size()+1);
                 clientPanel.updatePlayersList();
             }
         });
         timer.start();
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
