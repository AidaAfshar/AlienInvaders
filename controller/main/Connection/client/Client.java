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

public class Client extends Thread {

    Socket socket ;

    String IP ;
    int port ;

    Player clientPlayer;
    ClientPanel clientPanel ;

    ArrayList<Player> otherPlayers ;

    PrintWriter printer ;
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
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream())) ;


            sendClientPlayerToServer() ;
            receiveOtherPlayersFromServer() ;

            prepareTimer();
            prepareUpdateTimer();

            timer.start();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }


    private void sendClientPlayerToServer(){
        clientPlayer.save();
        printer.println(clientPlayer);
        printer.flush();
    }


    public void receiveOtherPlayersFromServer() throws IOException, InterruptedException {
        otherPlayers = new ArrayList<>();

        String data ;
        Thread.currentThread().sleep(500);
        while (reader.ready() &&(data = reader.readLine())!=null){
            Player player = DataManager.load(data) ;
            otherPlayers.add(player) ;
            clientPanel.addPlayer(player);
        }
    }


    void receiveNewPlayerFromServer() throws IOException {
        String data ;
        if(reader.ready() &&(data = reader.readLine())!=null){
            Player newPlayer = DataManager.load(data) ;
            otherPlayers.add(newPlayer) ;
            clientPanel.addPlayer(newPlayer);
        }

    }


    Timer timer ;
    public void prepareTimer(){
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    receiveNewPlayerFromServer();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }) ;

    }

    public void stopTimer(){
        timer.stop();
    }

    public void retartTimer(){
        timer.restart();
    }

    //during game:

    void sendDataToServer(){
        clientPlayer.save();
        printer.println(clientPanel);
        printer.flush();
    }

    Timer updateTimer ;
    public void prepareUpdateTimer(){
        updateTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendDataToServer();
            }
        });
    }

    public void stopUpdateTimer(){
        updateTimer.stop();
    }

    public void retartUpdateTimer(){
        updateTimer.restart();
    }

    public void startUpdateTimer(){
        updateTimer.start();
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
