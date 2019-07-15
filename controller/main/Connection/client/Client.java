package controller.main.Connection.client;

import controller.player.Player;
import model.dataManagement.DataManager;
import view.screen.ClientPanel;

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
    ClientPanel clientPanel ;

    ArrayList<Player> otherPlayers ;

    PrintWriter printer ;
//    Scanner scanner ;
    BufferedReader reader ;

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
            printer = new PrintWriter(socket.getOutputStream()) ;
//            scanner = new Scanner(socket.getInputStream()) ;
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;


            sendClientPlayerToServer() ;
            receiveOtherPlayersFromServer() ;


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
//        while (scanner.hasNextLine()){
//            Player player = DataManager.load(scanner.nextLine()) ;
//            otherPlayers.add(player) ;
//            clientPanel.addPlayer(player);
//        }
        String data ;
        while (reader.ready() &&(data = reader.readLine())!=null){
            Player player = DataManager.load(data) ;
            otherPlayers.add(player) ;
            clientPanel.addPlayer(player);
        }


            System.out.println("after while");
    }


//    void receiveNewPlayerFromServer(){
//        if(scanner.hasNextLine()){
//            Player newPlayer = DataManager.load(scanner.nextLine()) ;
//            otherPlayers.add(newPlayer) ;
//            clientPanel.addPlayer(newPlayer);
//        }
//    }


//    Timer timer ;
//    public void prepareTimer(){
//        timer = new Timer(3000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                receiveNewPlayerFromServer();
//            }
//        }) ;
//        timer.start();
//    }
//
//
//    public void evokeTimer(){
//        timer.start();
//    }
//
//     class NewPlayerReciever extends Thread{
//
//        Scanner scanner ;
//        Player newPlayer ;
//
//        public NewPlayerReciever(Scanner scanner){
//            this.scanner = scanner ;
//        }
//
//         @Override
//         public void run() {
//             super.run();
//             while (true) {
//                 if (scanner.hasNextLine()) {
//                     try {
//                         sleep(1000);
//                         newPlayer = DataManager.load(scanner.nextLine());
//                         otherPlayers.add(newPlayer) ;
//                         clientPanel.addPlayer(newPlayer);
//                         sleep(3000);
//                     } catch (InterruptedException e) {
//                         e.printStackTrace();
//                     }
//
//                 }
//             }
//         }
//
//
//     }

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
