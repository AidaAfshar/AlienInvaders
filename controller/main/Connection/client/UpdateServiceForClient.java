package controller.main.Connection.client;

import controller.player.Player;
import model.dataManagement.DataManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class UpdateServiceForClient {

    //Scanner scanner ;
    BufferedReader reader ;
    PrintWriter printer ;

    Player currentPlayer;
    ArrayList<Player> otherPlayers ;

    public UpdateServiceForClient(OutputStream outputStream, InputStream inputStream , Player player ,ArrayList<Player> otherPlayers){
        printer = new PrintWriter(outputStream ,false) ;
        //scanner = new Scanner(inputStream) ;
        reader = new BufferedReader(new InputStreamReader(inputStream));
        currentPlayer = player ;
        this.otherPlayers = otherPlayers ;
        initialize() ;
    }

    void initialize(){
        prepareUpdateTimer() ;
    }

    Timer updateTimer;
    public void prepareUpdateTimer(){
        updateTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    sendCurrentPlayersUpdate();
                    receiveOtherPlayersUpdate();


                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    void sendCurrentPlayersUpdate() {
        currentPlayer.save();
        printer.println(currentPlayer);
        printer.flush();
    }

    void receiveOtherPlayersUpdate() throws IOException, InterruptedException {
//        System.out.println("before loop");
//        for(Player player : otherPlayers){
//            System.out.println("inside loop");
//            String data = scanner.nextLine();
//            System.out.println("after next Line");
//            Player updatedPlayer = DataManager.load(data) ;
//            player.update(updatedPlayer);
//        }
//        System.out.println("after loop");

    }


    public void stopUpdateTimer(){
        updateTimer.stop();
    }

    public void restartUpdateTimer(){
        updateTimer.restart();
    }

    public void startUpdateTimer(){
        updateTimer.start();
    }



    //getter & setter

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

}
