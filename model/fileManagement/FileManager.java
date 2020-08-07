package model.fileManagement;

import controller.player.playerExtentions.Player;
import model.dataManagement.DataManager;
import model.dataManagement.GameState;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class FileManager {


    static String dataFileAddress = "src/model/fileManagement/game.data";
    static String stateFileAddress = "src/model/fileManagement/gameState";


    public FileManager(){
        initialize() ;
    }

    void initialize(){

    }

    public void save(Player player){
        try {
            PrintWriter printer = new PrintWriter (new FileWriter (dataFileAddress,true)) ;

            player.save();
            printer.println(player);
            printer.flush();


        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public Player load(String name){
        try {
            Scanner scanner=new Scanner(new FileReader(dataFileAddress));
            while(scanner.hasNextLine()){
                Player player=DataManager.load(scanner.nextLine());
                if(player.getName().equals(name)){
                    return player;
                }
            }

            scanner.close();
        }catch (Exception e){
            e.printStackTrace();
        }


        return null ;
    }


    public ArrayList<Player> getSavedList(){

        ArrayList<Player> players=new ArrayList<>();

        try {
            Scanner scanner=new Scanner(new FileReader(dataFileAddress));

            while(scanner.hasNextLine()){
                Player player=DataManager.load(scanner.nextLine());
                players.add(player);
            }

            scanner.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return players ;
    }

    public boolean includes(String name){
        try {
            Scanner scanner=new Scanner(new FileReader(dataFileAddress));

            while(scanner.hasNextLine()){

                Player player=DataManager.load(scanner.nextLine());
                if(player.getName().equals(name)){
                    return true;
                }
            }

            scanner.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        return false ;
    }

    public void save(GameState gameState){
        try {
            PrintWriter printer = new PrintWriter (new FileWriter (stateFileAddress,true)) ;

            printer.println(gameState);
            printer.flush();



        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
