package model.dataManagement;

import com.gilecode.yagson.YaGson;
import com.google.gson.Gson;
import controller.player.Player;

import java.io.InputStream;
import java.util.Scanner;

public class DataManager {

//    public static String save(Player player){
//        Gson gson = new Gson() ;
//        return gson.toJson(player) ;
//    }
//
//    public static Player load(InputStream inputStream){
//        Gson gson = new Gson() ;
//        Scanner scanner = new Scanner(inputStream) ;
//        return gson.fromJson(scanner.nextLine() , Player.class) ;
//    }

    public static String save(Player player){
        YaGson gson = new YaGson() ;
        return gson.toJson(player) ;
    }

    public static Player load(InputStream inputStream){
        YaGson gson = new YaGson() ;
        Scanner scanner = new Scanner(inputStream) ;
        return gson.fromJson(scanner.nextLine() , Player.class) ;
    }


}
