package controller.main.Connection.client;

import controller.player.Player;
import model.dataManagement.DataManager;
import view.screen.ClientPanel;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConnectionServiceForClient {

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
        initialize();
    }

    public void initialize(){
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

        }

    }



    //getter


    public ArrayList<Player> getOtherPlayers() {
        return otherPlayers;
    }
}
