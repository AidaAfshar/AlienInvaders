package model.database;


import controller.player.playerExtentions.Player;
import controller.ship.SpaceShip;
import model.dataManagement.DataManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static model.database.DatabaseClass.*;

public class DatabaseManager {

    Connection connection ;

    public DatabaseManager(){
        connectToDataBase();
    }

    public void connectToDataBase()  {
        String dbmsHost = "jdbc:mysql://localhost:3306/alieninvadersdatabase";
        String user     = "root";
        String password = "";

        try {


            connection = getConnection(dbmsHost, user, password);



        }  catch (SQLException e) {
            System.out.println("could not connect to database");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void addPlayerToDatabase(Player player){
        try {
            String string=getInsertString(
                    player.getName(),
                    player.getScore(), player.getCoin(), player.getPower(),player.getBombCount(),
                    player.getX(), player.getY());

            insertQuery(connection, string);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletePlayerFromDatabase(Player player) {
        try{
            String string = getDeleteString(player.getName()) ;
            deleteQuery(connection,string) ;
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deletePlayerFromDatabase(String playerName) {
        try{
            String string = getDeleteString(playerName) ;
            deleteQuery(connection,string) ;
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updatePlayer(Player player){
        try {

            String string=getUpdateString(
                    player.getName(),
                    player.getScore(), player.getCoin(), player.getPower(),player.getBombCount(),
                    player.getX(), player.getY());

            updateQuery(connection, string);

        }catch (SQLException e){
        e.printStackTrace();
        }
    }

    public Player getPlayersData(String playerName){


        return new Player(playerName,new SpaceShip()) ;
    }



    public ArrayList<Player> getPlayersArrayList()  {

        ArrayList<Player> players = new ArrayList<>();

        try {
            ResultSet resultSet=selectAllQuery(connection);

            ArrayList<String> names=new ArrayList<>();
            ArrayList<Integer> scores=new ArrayList<>();
            ArrayList<Integer> coins=new ArrayList<>();
            ArrayList<Integer> powers=new ArrayList<>();
            ArrayList<Integer> bombCounts=new ArrayList<>();
            ArrayList<Integer> x=new ArrayList<>();
            ArrayList<Integer> y=new ArrayList<>();


            int j=0;

            while(resultSet.next()){
                int i=1;
                names.add(resultSet.getObject(i).toString());
                i++;
                scores.add(Integer.valueOf(resultSet.getObject(i).toString()));
                i++;
                coins.add(Integer.valueOf(resultSet.getObject(i).toString()));
                i++;
                powers.add(Integer.valueOf(resultSet.getObject(i).toString()));
                i++;
                bombCounts.add(Integer.valueOf(resultSet.getObject(i).toString()));
                i++;
                x.add(Integer.valueOf(resultSet.getObject(i).toString()));
                i++;
                y.add(Integer.valueOf(resultSet.getObject(i).toString()));
                j++;
            }

            for (int i=0;i < j;i++){
                players.add(DataManager.instantiatePlayer(names.get(i),
                        scores.get(i), coins.get(i), powers.get(i), bombCounts.get(i),
                        x.get(i), y.get(i)));
            }

        }catch (Exception e){
            e.printStackTrace();
        }


        return players ;
    }


    public Player makePlayerFromData(ResultSet resultSet, int row){
        try {
            String string = resultSet.getString(row);
            return DataManager.load(string) ;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null ;
    }
}
//            Player player = new Player("aref",new SpaceShip()) ;
//            deletePlayerFromDatabase(player);

//            Player player = new Player("aref",new SpaceShip()) ;
//            addPlayerToDatabase(player);
//            ResultSet resultSet = selectAllQuery(connection) ;
//            printResultSet(resultSet);

//    deletePlayerFromDatabase("aref");
//    deletePlayerFromDatabase("liza");

//    Player player1 = DataManager.instantiatePlayer("liza",0,0,0,0,0,0);
//    Player player2 = DataManager.instantiatePlayer("liza",0,5,0,3,0,0);
//
//    addPlayerToDatabase(player1);
//    addPlayerToDatabase(player2);