package controller.controlSection.Connection.client;


import controller.player.playerExtentions.Player;
import model.dataManagement.DataManager;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UpdateServiceForClient {

    MyOutputStream outputStream ;
    MyInputStream inputStream ;

    Client client ;
    Player clientPlayer ;
    ArrayList<Player> otherPlayers ;

    public UpdateServiceForClient(OutputStream outputStream , InputStream inputStream , Client client){
        this.outputStream = new MyOutputStream(outputStream) ;
        this.inputStream = new MyInputStream(inputStream) ;
        this.client = client ;
    }

    public void initialize(){
        this.clientPlayer = client.getClientPlayer() ;
        this.otherPlayers = client.getOtherPlayers() ;

        outputStream.start();
        inputStream.start();
    }

    class MyOutputStream extends Thread {

        PrintWriter printer;

        MyOutputStream(OutputStream outputStream) {
            printer = new PrintWriter(outputStream);
        }

        @Override
        public void run() {
            super.run();

            try {

                enterGame();
                sendClientPlayersUpdateToClient() ;
                sleep(30);

            }catch (Exception e){
                e.printStackTrace();
            }



        }

        void enterGame(){
            printer.println("in");
            printer.flush();
        }

        void sendClientPlayersUpdateToClient() {
            if(clientPlayer.isInGame()) {
                clientPlayer.save();
                printer.println(clientPlayer);
                printer.flush();
            }
        }

    }

    //-------------------------------------------------------------

    boolean whileBoolean = true ;

    class MyInputStream extends Thread {

        Scanner scanner;

        MyInputStream(InputStream inputStream) {
            scanner = new Scanner(inputStream);
        }

        @Override
        public void run() {
            super.run();

            receiveOtherPlayersUpdatesFromServer() ;

        }

        void receiveOtherPlayersUpdatesFromServer(){

            while(whileBoolean){
                if(scanner.hasNextLine()){

                    String data = scanner.nextLine() ;
                    Player updatedPlayer = DataManager.load(data) ;

                    //TODO see which one works:

                    findPlayer(updatedPlayer).update(updatedPlayer);

//                    Player player = findPlayer(updatedPlayer) ;
//                    player.update(updatedPlayer);

                }else break;
            }
        }

    }

    Player findPlayer (Player samplePlayer){
        for(Player player : otherPlayers){
            if(player.equals(samplePlayer))
                return player ;
        }
        return null ;
    }

    //getters & setters:


    public boolean getWhileBoolean() {
        return whileBoolean;
    }

    public void setWhileBoolean(boolean whileBoolean) {
        this.whileBoolean = whileBoolean;
    }
}//end of class
