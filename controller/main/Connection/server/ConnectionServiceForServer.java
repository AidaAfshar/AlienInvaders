package controller.main.Connection.server;

import controller.player.Player;
import model.dataManagement.DataManager;
import view.screen.ServerPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConnectionServiceForServer {

    MyOutputStream outputStream ;
    MyInputStream inputStream ;

    ServerPanel serverPanel ;

    ArrayList<Player> otherPlayers ;
    Player clientPlayer;

    public ConnectionServiceForServer(OutputStream outputStream, InputStream inputStream , ArrayList<Player> otherPlayers , ServerPanel serverPanel ) {
        this.outputStream = new MyOutputStream(outputStream) ;
        this.inputStream = new MyInputStream(inputStream) ;
        this.otherPlayers = otherPlayers ;
        this.serverPanel = serverPanel ;
        initialize();
    }


    public void initialize(){
        outputStream.start();
        inputStream.start();
    }


    class MyOutputStream extends Thread{

        PrintWriter printer ;

        MyOutputStream(OutputStream outputStream ){
            printer = new PrintWriter(outputStream) ;
        }

        @Override
        public void run() {
            super.run();

            sendOtherPlayersToClient();

        }

        void sendOtherPlayersToClient(){
            for (Player player : otherPlayers) {
                player.save();
                printer.println(player);
                printer.flush();
            }
        }

        void sendNewPlayerToClient(Player player){
            player.save();
            printer.println(player);
            printer.flush();
        }
    }

   //------------------------------------------------------------

    class MyInputStream extends Thread{

        Scanner scanner ;

        MyInputStream(InputStream inputStream){
            scanner = new Scanner(inputStream) ;
        }

        @Override
        public void run() {
            super.run();

            loadNewPlayer();

        }

        void loadNewPlayer() {

            while (true){
                if(scanner.hasNextLine()){
                    String data =scanner.nextLine();
                    if(data != null){
                        setClientPlayer(DataManager.load(data));
                        updateServerPanel();
                    }

                }else break;
            }

        }

    }


    void updateServerPanel(){
        serverPanel.addPlayer(clientPlayer.getName());
    }

    public void updatePlayersList(Player player){
        outputStream.sendNewPlayerToClient(player);
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
}
