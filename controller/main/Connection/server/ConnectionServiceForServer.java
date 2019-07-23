package controller.main.Connection.server;

import controller.player.Player;
import model.dataManagement.DataManager;
import view.screen.ServerPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ConnectionServiceForServer extends Thread{

    PrintWriter printer ;
    BufferedReader reader ;

    ServerPanel serverPanel ;

    ArrayList<Player> otherPlayers ;
    Player clientPlayer;

    public ConnectionServiceForServer(OutputStream outputStream, InputStream inputStream , ArrayList<Player> otherPlayers , ServerPanel serverPanel ) {
        printer = new PrintWriter(outputStream) ;
        reader = new BufferedReader(new InputStreamReader(inputStream)) ;
        this.otherPlayers = otherPlayers ;
        this.serverPanel = serverPanel ;
    }


    @Override
    public void run() {
        super.run();

        try {

            sendOtherPlayersToClient();
            loadNewPlayer();




        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void sendOtherPlayersToClient(){
        for (Player player : otherPlayers) {
            player.save();
            printer.println(player);
            printer.flush();
        }
    }

    private void sendNewPlayerToClient(Player player){
        player.save();
        printer.println(player);
        printer.flush();
    }

    private void loadNewPlayer() throws IOException, InterruptedException {
        String data ;
        Thread.currentThread().sleep(250);
        while (reader.ready() && (data = reader.readLine())!=null){
            clientPlayer = DataManager.load(data);
        }
        updateServerPanel();
    }

    void updateServerPanel(){
        serverPanel.addPlayer(clientPlayer.getName());
    }

    public void updatePlayersList(Player player){
        //otherPlayers.add(player) ;     -_____-
        sendNewPlayerToClient(player);
    }

    //during game:

    public void updatePlayerData() throws IOException {
        String data ;
        if (reader.ready() && (data = reader.readLine())!=null){
            clientPlayer = DataManager.load(data);
        }
    }

    Timer updateTimer ;
    public void prepareUpdateTimer(){
        updateTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updatePlayerData();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
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
}
