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
    private int levelsCount ;
    private int playersCount ;

    ArrayList<Player> players = new ArrayList<>() ;




    public Server(ServerPanel panel, int port , int levelsCount , int playersCount) {
        super() ;
        this.panel = panel ;
        this.port = port ;
        this.levelsCount = levelsCount ;
        this.playersCount = playersCount ;
        initialize();
    }


    private void initialize(){
        panel.setPlayersCount(playersCount);
        panel.initialize();
    }

    @Override
    public void run() {
        super.run();

        try {
            serverSocket = new ServerSocket(port) ;

            while (true){

//                System.out.println("inside run-server before accept");
                Socket socket = serverSocket.accept() ;
//                System.out.println("inside run-server after accept");
                Player player = DataManager.load(socket.getInputStream());
//                System.out.println(player.getPlayerName()) ;
//                player.setInputStream(socket.getInputStream());
//                player.setOutputStream(socket.getOutputStream());
                player.setPanel(panel);
                players.add(player) ;
                player.preparePlayerThread();

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

    public int getLevelsCount() {
        return levelsCount;
    }

    public void setLevelsCount(int levelsCount) {
        this.levelsCount = levelsCount;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }


}
