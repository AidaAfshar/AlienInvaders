package controller.main.Connection.server;

import controller.main.Connection.ConnectionService;
import controller.main.Connection.UpdateService;
import controller.player.Player;
import view.screen.ServerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {


    ServerSocket serverSocket ;

    ServerPanel panel ;

    private int port ;

    ArrayList<Player> players = new ArrayList<>() ;
    ArrayList<Player> otherPlayers ;
    Player serverPlayer ;

    ArrayList<ConnectionService> clientsConnections = new ArrayList<>();
    ArrayList<UpdateService> clientsUpdates = new ArrayList<>();


    public Server( int port ,  ServerPanel panel) {
        super() ;
        this.port = port ;
        this.panel = panel ;
        initialize();
    }

    public Server( int port ,  Player serverPlayer ,  ServerPanel panel ) {
        super() ;
        this.port = port ;
        this.serverPlayer = serverPlayer ;
        this.panel = panel ;
        initialize();
    }

    private void initialize(){
        players.add(serverPlayer) ;
        panel.initialize();
    }

    @Override
    public void run() {
        super.run();

        try {

            serverSocket = new ServerSocket(port) ;

            while (true){

                Socket socket = serverSocket.accept() ;

                ConnectionService connectionService = new ConnectionService(
                        socket.getOutputStream(),
                        socket.getInputStream(),
                        players) ;

                prepareConnection(connectionService) ;
                Player player = connectionService.getNewPlayer() ;
                addNewPlayer(player) ;
                sendNewPlayerToOtherClients(player);

                UpdateService updateService = new UpdateService(
                        socket.getOutputStream(),
                        socket.getInputStream(),
                        player) ;


                clientsConnections.add(connectionService) ;
                clientsUpdates.add(updateService) ;

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    void prepareConnection(ConnectionService connectionService) throws InterruptedException {
        connectionService.start();
        connectionService.join();
    }

    void addNewPlayer(Player player){
        panel.addPlayer(player.getName());
        players.add(player) ;
        player.preparePlayer();
    }


    void sendNewPlayerToOtherClients(Player player){
        for(ConnectionService client : clientsConnections)
            client.updatePlayersList(player);

    }

    //during game:

    ArrayList<Player> updatedPlayers ;

    void updatePlayersState(){
        updatedPlayers = new ArrayList<>() ;
        for(UpdateService client : clientsUpdates){
            updatedPlayers.add(client.getCurrentPlayer()) ;
        }
        players = updatedPlayers ;
    }


    Timer updateTimer ;
    public void prepareUpdateTimer(){
        updateTimer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePlayersState();
            }
        });
        updateTimer.start();
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


    public void startUpdating(){
        for(UpdateService service :clientsUpdates)
            service.startUpdateTimer();
    }

    public void stopUpdating(){
        for(UpdateService service :clientsUpdates)
            service.stopUpdateTimer();
    }

    public ArrayList<Player> getUpdatedPlayers() {
        return updatedPlayers;
    }

    //getters & setters :


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Player getServerPlayer() {
        return serverPlayer;
    }

    public void setServerPlayer(Player serverPlayer) {
        this.serverPlayer = serverPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> getOtherPlayers() {
        otherPlayers = new ArrayList<>() ;

        for(int i=1 ; i<players.size() ;i++)
            otherPlayers.add(players.get(i)) ;

        return otherPlayers;
    }
}
