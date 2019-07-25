package controller.main.Connection.server;

import controller.player.Player;
import controller.player.PlayerState;
import model.dataManagement.DataManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UpdateServiceForServer {

    Scanner scanner ;
    PrintWriter printer ;

    Player clientPlayer ;
    ArrayList<Player> otherPlayers ;

    public UpdateServiceForServer(OutputStream outputStream, InputStream inputStream , Player player , ArrayList<Player> otherPlayers){
        printer = new PrintWriter(outputStream,false) ;
        scanner = new Scanner(inputStream) ;
        clientPlayer = player ;
        this.otherPlayers = otherPlayers ;
        initialize() ;
    }

    void initialize(){
        prepareUpdateTimer() ;
    }


    Timer timer ;
    public void prepareUpdateTimer(){
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(clientPlayer!=null && clientPlayer.getState()==PlayerState.INGAME) {
                    sendOtherPlayersUpdateForClient();
                    receiveClientPlayersUpdate();
                }
            }
        });
    }

    void sendOtherPlayersUpdateForClient(){
        for(Player player : otherPlayers){
            player.save();
            printer.println(player);
            printer.flush();
        }
    }

    void receiveClientPlayersUpdate(){
        if(scanner.hasNextLine()) {
            String data = scanner.nextLine();
            System.out.println("after nextLine");
            Player updatedPlayer = DataManager.load(data);
            clientPlayer.update(updatedPlayer);
        }else return;
    }



    public void stopUpdateTimer(){
        timer.stop();
    }

    public void restartUpdateTimer(){
        timer.restart();
    }

    public void startUpdateTimer(){
        timer.start();
    }

    //getter


    public Player getClientPlayer() {
        return clientPlayer;
    }
}
