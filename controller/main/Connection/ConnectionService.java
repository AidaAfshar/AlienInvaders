package controller.main.Connection;

import controller.player.Player;
import model.dataManagement.DataManager;

import java.io.*;
import java.util.ArrayList;

public class ConnectionService extends Thread{

    PrintWriter printer ;
    BufferedReader reader ;

    ArrayList<Player> otherPlayers ;
    Player newPlayer ;

    public ConnectionService(OutputStream outputStream,InputStream inputStream ,ArrayList<Player> otherPlayers) {
        printer = new PrintWriter(outputStream) ;
        reader = new BufferedReader(new InputStreamReader(inputStream)) ;

        this.otherPlayers = otherPlayers ;
    }


    @Override
    public void run() {
        super.run();


        sendOtherPlayersToClient();
        try {
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
            newPlayer = DataManager.load(data);
        }
    }

    public void updatePlayersList(Player player){
        //otherPlayers.add(player) ;     -_____-
        sendNewPlayerToClient(player);
    }


    //getters & setters:


    public Player getNewPlayer() {
        return newPlayer;
    }

    public void setNewPlayer(Player newPlayer) {
        this.newPlayer = newPlayer;
    }
}
