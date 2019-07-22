package controller.main.Connection;

import controller.player.Player;
import model.dataManagement.DataManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class UpdateService {

    Scanner scanner ;
    PrintWriter printer ;

    Player currentPlayer;
    ArrayList<Player> otherPlayers ;

    public UpdateService(OutputStream outputStream, InputStream inputStream ,Player player){
        printer = new PrintWriter(outputStream , false) ;
        scanner = new Scanner(inputStream) ;
        currentPlayer = player ;
        initialize() ;
    }

    void initialize(){
        prepareUpdateTimer() ;
    }

    void sendCurrentPlayersUpdate() {
        currentPlayer.save();
        printer.println(currentPlayer);
        printer.flush();
    }

    void receiveOtherPlayersUpdate() {
        for(Player player : otherPlayers){
            String data = scanner.nextLine();
            System.out.println(data);
            Player updatedPlayer = DataManager.load(data) ;
            player.update(updatedPlayer);
        }
    }

    Timer timer ;
    public void prepareUpdateTimer(){
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendCurrentPlayersUpdate();
                receiveOtherPlayersUpdate();
            }
        });
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



    //getter & setter

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

}
