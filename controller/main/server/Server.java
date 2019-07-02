package controller.main.server;

import controller.main.client.Client;
import controller.player.Player;
import model.dataManagement.DataManager;
import view.screen.ContentPane;
import view.screen.ServerPanel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

    ServerSocket serverSocket ;

    ServerPanel panel ;

    private int port ;

    ArrayList<Player> players = new ArrayList<>() ;


    public Server( int port ,  ServerPanel panel) {
        super() ;
        this.panel = panel ;
        this.port = port ;
        initialize();
    }


    private void initialize(){
        panel.initialize();
    }

    @Override
    public void run() {
        super.run();

        try {
            serverSocket = new ServerSocket(port) ;

            while (true){

                Socket socket = serverSocket.accept() ;
                Player player = DataManager.load(socket.getInputStream());
                panel.addPlayer(player.getName());
                players.add(player) ;
                player.preparePlayer();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //getters & setters :


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public ArrayList<Player> getPlayers() {
        return players;
    }
}
