package model.fileManagement;

import controller.player.playerExtentions.Player;
import model.dataManagement.DataManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class FileManager {

    PrintWriter printer ;
    Scanner scanner ;

    LineRemover lineRemover ;

    public FileManager(){
        initialize() ;
    }

    void initialize(){
        try {


            printer = new PrintWriter (new FileWriter ("src/model/fileManagement/game.data",true)) ;
            scanner = new Scanner (new FileReader ("src/model/fileManagement/game.data")) ;
            //lineRemover = new LineRemover("src/model/fileManagement/game.data") ;



        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public  void save(Player player){
        player.save ();
        printer.println (player);
        printer.flush ();
    }


    public Player load(String name){
        while (scanner.hasNextLine ()){
            Player player = DataManager.load (scanner.nextLine ());
            if(player.getName ().equals (name)) {
                return player;
            }
        }
        return null ;
    }


    public ArrayList<Player> getSavedList(){

        ArrayList<Player> players = new ArrayList<> () ;

        while (scanner.hasNextLine ()){
            Player player = DataManager.load (scanner.nextLine ());
            players.add (player) ;
        }

        return players ;
    }

    public boolean includes(String name){
        while (scanner.hasNextLine ()){

            Player player = DataManager.load (scanner.nextLine ());
            if(player.getName ().equals (name)) {
                return true;
            }
        }
        return false ;
    }

}
