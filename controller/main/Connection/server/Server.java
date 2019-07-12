package controller.main.Connection.server;

import controller.main.Connection.ConnectionService;
import controller.player.Player;
import view.screen.ServerPanel;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server extends Thread {

    public static Object synchObject = new Object();

    ServerSocket serverSocket ;
    Scanner scanner ;
    PrintWriter printer ;

    ServerPanel panel ;

    private int port ;

    ArrayList<Player> players = new ArrayList<>() ;
    Player serverPlayer ;

    ArrayList<ConnectionService> clients = new ArrayList<>();

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
                ConnectionService  service = new ConnectionService(
                        socket.getOutputStream(),
                        socket.getInputStream(),
                        players) ;

                clients.add(service) ;
                service.start();
                service.join();

                Player player = service.getNewPlayer() ;
                addNewPlayer(player) ;
                sendNewPlayerToOtherClients(player);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    void addNewPlayer(Player player){
        panel.addPlayer(player.getName());
        players.add(player) ;
        player.preparePlayer();
    }


    void sendNewPlayerToOtherClients(Player player){
        for(ConnectionService client :clients){
            client.updatePlayersList(player);
        }
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
}
