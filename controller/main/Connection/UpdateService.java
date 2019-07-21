package controller.main.Connection;

import controller.player.Player;
import model.dataManagement.DataManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;


public class UpdateService {

    Scanner scanner ;
    PrintWriter printer ;

    Player updatedPlayer ;

    public UpdateService(OutputStream outputStream, InputStream inputStream ,Player player){
        printer = new PrintWriter(outputStream) ;
        scanner = new Scanner(inputStream) ;
        updatedPlayer = player ;
        initialize() ;
    }

    void initialize(){
        prepareUpdateTimer() ;
    }

    void updatePlayersData() {
        updatedPlayer = DataManager.load(scanner.nextLine()) ;
    }

    Timer timer ;
    public void prepareUpdateTimer(){
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePlayersData();
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

    public Player getUpdatedPlayer() {
        return updatedPlayer;
    }

}
