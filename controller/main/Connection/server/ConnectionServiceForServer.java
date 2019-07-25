package controller.main.Connection.server;

import controller.player.Player;
import model.dataManagement.DataManager;
import view.screen.ServerPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConnectionServiceForServer{

    MyOutputStream outputStream ;
    MyInputStream inputStream ;

    ServerPanel serverPanel ;

    ArrayList<Player> otherPlayers ;
    Player clientPlayer;

    public ConnectionServiceForServer(OutputStream outputStream, InputStream inputStream , ArrayList<Player> otherPlayers , ServerPanel serverPanel ) {
        this.outputStream = new MyOutputStream(outputStream,otherPlayers) ;
        this.inputStream = new MyInputStream(inputStream) ;
        this.otherPlayers = otherPlayers ;
        this.serverPanel = serverPanel ;
        initialize() ;
    }

    void initialize(){
        outputStream.start();
        inputStream.start();
    }

    class MyOutputStream extends Thread{

        PrintWriter printer ;
        ArrayList<Player> otherPlayers ;

        MyOutputStream(OutputStream outputStream , ArrayList<Player> otherPlayers){
            printer = new PrintWriter(outputStream) ;
            this.otherPlayers = otherPlayers ;
        }

        @Override
        public void run() {
            super.run();

            sendOtherPlayersToClient() ;

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

            try {

                loadNewPlayer() ;

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        void loadNewPlayer() throws IOException, InterruptedException {
            while (scanner.hasNextLine()){
                String data =scanner.nextLine();
                clientPlayer = DataManager.load(data);
            }
            updateServerPanel();
        }

    }


    void updateServerPanel(){
        serverPanel.addPlayer(clientPlayer.getName());
    }

    public void updatePlayersList(Player player){
        outputStream.sendNewPlayerToClient(player);
    }

    //during game:

//    public void updatePlayerData() throws IOException {
//        String data ;
//        if (reader.ready() && (data = reader.readLine())!=null){
//            clientPlayer = DataManager.load(data);
//        }
//    }
//
//    Timer updateTimer ;
//    public void prepareUpdateTimer(){
//        updateTimer = new Timer(30, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                try {
//                    updatePlayerData();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
//    }
//
//    public void stopUpdateTimer(){
//        updateTimer.stop();
//    }
//
//    public void retartUpdateTimer(){
//        updateTimer.restart();
//    }
//
//    public void startUpdateTimer(){
//        updateTimer.start();
//    }

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
