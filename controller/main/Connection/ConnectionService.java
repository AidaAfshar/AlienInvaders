package controller.main.Connection;

import controller.player.Player;
import model.dataManagement.DataManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ConnectionService extends Thread{

//    PrintStream printer ;
//    Scanner scanner ;

    PrintWriter printer ;
    BufferedReader reader ;



//    File sampleFile = new File("C:\\Users\\User\\IdeaProjects\\AlienInvaders\\src\\model\\utilities\\SampleFile") ;
//    File erfan = new File("C:\\Users\\User\\IdeaProjects\\AlienInvaders\\src\\model\\utilities\\erfan") ;
//    File liza = new File("C:\\Users\\User\\IdeaProjects\\AlienInvaders\\src\\model\\utilities\\liza") ;
//    File aref = new File("C:\\Users\\User\\IdeaProjects\\AlienInvaders\\src\\model\\utilities\\aref") ;



//    PrintStream samplePrinter;



    ArrayList<Player> otherPlayers ;
    Player newPlayer ;

    public ConnectionService(OutputStream outputStream,InputStream inputStream ,ArrayList<Player> otherPlayers) {
        printer = new PrintWriter(outputStream) ;
        reader = new BufferedReader(new InputStreamReader(inputStream)) ;

//        scanner = new Scanner(inputStream) ;
        this.otherPlayers = otherPlayers ;
//        initialize() ;
    }

//    void initialize() throws FileNotFoundException {
//            //samplePrinter = new PrintStream(sampleFile);
//            //samplePrinter = new PrintStream(erfan);
//            //samplePrinter = new PrintStream(liza);
//            samplePrinter = new PrintStream(aref);
//
//    }

    @Override
    public void run() {
        super.run();


        sendOtherPlayersToClient();
        try {
            loadNewPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void sendOtherPlayersToClient(){
        for (Player player : otherPlayers) {
    //        System.out.println(player.getName());
            player.save();
            printer.println(player);
            printer.flush();
//            samplePrinter.println(player);
//            samplePrinter.flush();
        }
    //    System.out.println("------------------------");
    }

    private void sendNewPlayerToClient(Player player){
        player.save();
        printer.println(player);
        printer.flush();
//        samplePrinter.println("*"+player);
//        samplePrinter.flush();
    }

    private void loadNewPlayer() throws IOException {
        String data ;
        while (reader.ready() && (data = reader.readLine())!=null){
            System.out.println("before load");
            newPlayer = DataManager.load(data);
            System.out.println("after load");
        }
        System.out.println("after while");
    }

    public void updatePlayersList(Player player){
        otherPlayers.add(player) ;
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
