package controller.main.server;

import controller.main.client.Client;
import controller.player.Player;
import model.dataManagement.DataManager;
import view.screen.ContentPane;
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
                printer = new PrintWriter(socket.getOutputStream()) ;
                scanner = new Scanner(socket.getInputStream()) ;
                sendOtherPlayersToClient();
                Player player = DataManager.load(scanner);
                panel.addPlayer(player.getName());
                players.add(player) ;
                player.preparePlayer();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void sendOtherPlayersToClient(){
        for (Player player : players) {
            player.save();
            printer.println(player);
            printer.flush();
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
