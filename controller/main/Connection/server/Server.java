package controller.main.Connection.server;

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
    private int port ;

    ServerPanel serverPanel;


    ArrayList<Player> players = new ArrayList<>() ;
    ArrayList<Player> otherPlayers ;
    Player serverPlayer ;

    ArrayList<ConnectionServiceForServer> clientsConnections = new ArrayList<>();
    ArrayList<UpdateServiceForServer> clientsUpdates = new ArrayList<>();


    public Server( int port ,  Player serverPlayer ,  ServerPanel panel ) {
        super() ;
        this.port = port ;
        this.serverPlayer = serverPlayer ;
        this.serverPanel = panel ;
        initialize();
    }

    private void initialize(){
        players.add(serverPlayer) ;
        serverPanel.initialize();
    }

    Player clientPlayer;

    @Override
    public void run() {
        super.run();

        try {

            serverSocket = new ServerSocket(port) ;

            while (true){

                Socket socket = serverSocket.accept() ;


                ConnectionServiceForServer connectionService = new ConnectionServiceForServer(
                        socket.getOutputStream(),
                        socket.getInputStream(),
                        players,
                        serverPanel) ;

                prepareConnection(connectionService) ;

                UpdateServiceForServer updateService = new UpdateServiceForServer(
                        socket.getOutputStream(),
                        socket.getInputStream(),
                        clientPlayer,
                        connectionService.getOtherPlayers()) ;


                clientsConnections.add(connectionService) ;
                clientsUpdates.add(updateService) ;

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    void prepareConnection(ConnectionServiceForServer connectionService) throws InterruptedException {
        connectionService.start();
        connectionService.join();

        Thread.sleep(500);
        clientPlayer = connectionService.getClientPlayer() ;
        addNewPlayer(clientPlayer);
        sendNewPlayerToOtherClients(clientPlayer);

    }

    void addNewPlayer(Player player){
        players.add(player) ;
        player.preparePlayer();
    }


    void sendNewPlayerToOtherClients(Player player){
        for(ConnectionServiceForServer client : clientsConnections)
            client.updatePlayersList(player);

    }

    //during game:

    ArrayList<Player> updatedPlayers ;

    void updatePlayersState(){
        updatedPlayers = new ArrayList<>() ;
        for(UpdateServiceForServer client : clientsUpdates){
            updatedPlayers.add(client.getClientPlayer()) ;
        }
        otherPlayers = updatedPlayers ;
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


    public void startUpdating(){
        for(UpdateServiceForServer service :clientsUpdates)
            service.startUpdateTimer();
    }

    public void stopUpdating(){
        for(UpdateServiceForServer service :clientsUpdates)
            service.stopUpdateTimer();
    }


    //getters & setters :

    public ArrayList<Player> getUpdatedPlayers() {
        return updatedPlayers;
    }

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
        {
            otherPlayers = new ArrayList<>();

            for (int i = 1; i < players.size(); i++)
                otherPlayers.add(players.get(i));
        }

        return otherPlayers;
    }
}
