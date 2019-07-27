package controller.main.Connection.server;


import controller.player.Player;
import model.dataManagement.DataManager;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class UpdateServiceForServer {


    MyOutputStream outputStream ;
    MyInputStream inputStream ;

    ArrayList<Player> otherPlayers ;

    public UpdateServiceForServer(OutputStream outputStream, InputStream inputStream , ArrayList<Player> otherPlayers){
        this.outputStream = new MyOutputStream(outputStream) ;
        this.inputStream = new MyInputStream(inputStream) ;
        this.otherPlayers = otherPlayers ;
    }


    public void initialize(){
        //outputStream.start();
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

                sendOtherPlayersUpdateToClient() ;
                sleep(30);

            }catch (Exception e){
                e.printStackTrace();
            }



        }


        void sendOtherPlayersUpdateToClient(){
            for(Player player : otherPlayers){
                if(player.isInGame()) {
                    player.save();
                    printer.println(player);
                    printer.flush();
                }
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

            waitForClientToEnterGame();
            updateOtherPlayers() ;
        }

        void waitForClientToEnterGame(){
            while(true){
                String data = scanner.nextLine();
                if(data.equals("in")){
                    outputStream.start();
                    break;
                }
            }
        }

        void updateOtherPlayers(){

            //inja faghat bayad dataye hamin cliente rooye khato bekhooni

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
