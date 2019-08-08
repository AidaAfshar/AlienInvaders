package model.database;



public class PlayerQuery {


    public static String getInsertString(String name , int score , int coin , int power ,int bombCount , int x , int y){
        return "INSERT INTO Players (name, score, coin, power, bombCount , x , y) values('"+name+"',"+score+","+coin+","+power+","+bombCount +","+x+","+y+")" ;
    }

    public static String getDeleteString(String name){
        return "DELETE FROM Players WHERE name ='"+name+"'" ;
    }


    public static String getUpdateString(String name , int score , int coin , int power ,int bombCount , int x , int y){
        return "UPDATE Players SET score = "+score+", coin="+coin+", power="+power+", bombCount="+bombCount+",x="+x+",y="+y+" WHERE name='"+name+"'" ;
    }


    public static String getSelectString(String wantedVariable){
        return "SELECT name ,"+ wantedVariable +"FROM Players" ;
    }


    public static String getSelectAllString(){
        return "SELECT * From Players" ;
    }
}
