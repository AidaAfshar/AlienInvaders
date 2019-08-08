package model.database;

import controller.enemy.alienGroups.GroupType;
import model.dataManagement.GameState;

public class StateQuery {

    public static String getInsertString(String name , GroupType groupType , int deadsCount){
        return "INSERT INTO gameState (name, groupType, deadsCount) values('"+name+"',"+groupType+","+deadsCount+")" ;
    }

    public static String getDeleteString(String name){
        return "DELETE FROM gamestate WHERE name ='"+name+"'" ;
    }


    public static String getUpdateString(String name , GroupType groupType , int deadsCount){
        return "UPDATE gameState SET groupType = "+groupType+", deadsCount="+deadsCount+" WHERE name='"+name+"'" ;
    }

    public static String getSelectString(String wantedVariable){
        return "SELECT name ,"+ wantedVariable +"FROM gamestate" ;
    }

    public static String getSelectAllString(){
        return "SELECT * From gamestate" ;
    }

}
