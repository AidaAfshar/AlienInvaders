package model.dataManagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.player.playerExtentions.Player;

import javax.xml.crypto.Data;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class DataManager {

    public static Player load(InputStream inputStream){

        GsonBuilder gsonBuilder = new GsonBuilder() ;
        Gson gson = gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT).create() ;

        Scanner scanner = new Scanner(inputStream) ;
        String data = scanner.nextLine() ;
        scanner.close();

        Player player = gson.fromJson(data, Player.class) ;
        return player ;
    }


    public static Player load(Scanner scanner){

        GsonBuilder gsonBuilder = new GsonBuilder() ;
        Gson gson = gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT).create() ;

        String data = scanner.nextLine() ;
        scanner.close();

        Player player = gson.fromJson(data, Player.class) ;
        return player ;
    }


    public static Player load(String data){

        GsonBuilder gsonBuilder = new GsonBuilder() ;
        Gson gson = gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT).create() ;

        Player player = gson.fromJson(data, Player.class) ;
        return player ;
    }

    public static String getJsonString(String name,int score,int coin , int power,int bombCount , int x , int y){
        return "{" +
                "name=" + name +
                ", score=" + score +
                ", coin=" + coin +
                ", power=" + power +
                ", bombCount=" + bombCount +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public static Player instantiatePlayer(String name,int score,int coin , int power,int bombCount , int x , int y){
        String data = "{" +
                "name=" + name +
                ", score=" + score +
                ", coin=" + coin +
                ", power=" + power +
                ", bombCount=" + bombCount +
                ", x=" + x +
                ", y=" + y +
                '}';

        return DataManager.load(data) ;
    }

}


//    public static String save(Player player){
//        Gson gson = new Gson() ;
//        return gson.toJson(player) ;
//    }

//    public static String save(Player player){
//        YaGson gson = new YaGson() ;
//        return gson.toJson(player) ;
//    }
//
//    public static Player load(InputStream inputStream){
//    //    System.out.println("inside load-DataManager 1");
//        YaGsonBuilder gsonBuilder = new YaGsonBuilder() ;
//        YaGson gson = gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT).create() ;
//
//        //    System.out.println("inside load-DataManager 2");
//
//        Scanner scanner = new Scanner(inputStream) ;
//        String data = scanner.nextLine() ;
//        scanner.close();
//        System.out.println(data);
//
//        Player player = gson.fromJson(data , Player.class) ;
//    //    System.out.println("inside load-DataManager 3");
//        System.out.println(player.getPlayerName());
//
//        return player ;
//    }
