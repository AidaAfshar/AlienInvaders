package controller.main.Connection.client;

import controller.player.Player;
import model.dataManagement.DataManager;
import view.screen.ClientPanel;

import javax.swing.*;
import javax.swing.plaf.synth.SynthTabbedPaneUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConnectionServiceForClient extends Thread{

    MyOutputStream outputStream ;
    MyInputStream inputStream ;

    ClientPanel clientPanel ;

    Player clientPlayer;
    ArrayList<Player> otherPlayers ;

    public ConnectionServiceForClient(OutputStream outputStream, InputStream inputStream,Player clientPlayer , ClientPanel clientPanel)  {
        this.outputStream = new MyOutputStream(outputStream) ;
        this.inputStream = new MyInputStream(inputStream) ;
        this.clientPlayer = clientPlayer ;
        this.clientPanel = clientPanel ;
    }

    @Override
    public void run() {
        super.run();

        outputStream.start();
        inputStream.start();


    }

    class MyOutputStream extends Thread{

        PrintWriter printer ;

        MyOutputStream(OutputStream outputStream){
            printer = new PrintWriter(outputStream) ;

        }

        @Override
        public void run() {
            super.run();

            try {

                    sendClientPlayerToServer();


            }catch (Exception e){}


        }

        void sendClientPlayerToServer(){
            clientPlayer.save();
            printer.println(clientPlayer);
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

            receiveOtherPlayersFromServer();

        }


        void receiveOtherPlayersFromServer() {
            otherPlayers = new ArrayList<>();

            while (true){
                if(scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    Player player = DataManager.load(data);
                    otherPlayers.add(player);
                    clientPanel.addPlayer(player);
                }else break;
            }

            System.out.println("after while");

        }

        void receiveNewPlayerFromServer() throws IOException {
            if(scanner.hasNextLine()){
                String data =scanner.nextLine();
                Player newPlayer = DataManager.load(data) ;
                otherPlayers.add(newPlayer) ;
                clientPanel.addPlayer(newPlayer);
            }

        }
    }



    Timer timer ;
    public void prepareTimer(){
        timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    inputStream.receiveNewPlayerFromServer();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }) ;

    }

    public void stopTimer(){
        timer.stop();
    }

    public void restartTimer(){
        timer.restart();
    }


    //getter


    public ArrayList<Player> getOtherPlayers() {
        return otherPlayers;
    }
}
