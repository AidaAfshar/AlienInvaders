package model.fileManagement;

import controller.player.playerExtentions.Player;
import model.dataManagement.DataManager;
import model.dataManagement.GameState;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class FileManager {

    PrintWriter printer ;
    Scanner scanner ;

    static String dataFileAddress = "src/model/fileManagement/game.data";
    static String stateFileAddress = "src/model/fileManagement/gameState";


    public FileManager(){
        initialize() ;
    }

    void initialize(){
//        try {
//
//
//            printer = new PrintWriter (new FileWriter ("src/model/fileManagement/game.data",true)) ;
//            scanner = new Scanner (new FileReader ("src/model/fileManagement/game.data")) ;
//
//
//
//        } catch (IOException e) {
//            e.printStackTrace ();
//        }
    }

    public void save(Player player){
        try {
            PrintWriter printer = new PrintWriter (new FileWriter (dataFileAddress,true)) ;

            player.save();
            printer.println(player);
            printer.flush();


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            printer.close();
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
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            scanner.close();
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

        }catch (Exception e){
            e.printStackTrace();
        }finally{
            scanner.close();
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
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            scanner.close();
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
        }finally {
            printer.close();
        }
    }



}
