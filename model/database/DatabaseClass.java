package model.database;

import java.sql.*;

public class DatabaseClass {

    public static Connection getConnection(String dbmsHost, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(dbmsHost,user,password);
    }


    static void printResultSet(ResultSet resultset) throws SQLException {
        System.out.println("---- Print the result ----");
        while(resultset.next()) {
            int size = resultset.getMetaData().getColumnCount();
            for(int i=1;i<=size;i++) {
                System.out.print("(");
                System.out.print(resultset.getMetaData().getColumnName(i));
                System.out.print(", ");
                System.out.print(resultset.getMetaData().getColumnType(i));
                System.out.print("): ");
                System.out.println(resultset.getObject(i).toString());
            }
            System.out.println("---- End of row ----");
        }
        System.out.println("---- End of result ----");
    }


    public static int insertQuery(Connection connection,String string) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(string);
    }


    public static String getInsertString(String name , int score , int coin , int power ,int bombCount , int x , int y){
        return "INSERT INTO Players (name, score, coin, power, bombCount , x , y) values('"+name+"',"+score+","+coin+","+power+","+bombCount +","+x+","+y+")" ;
    }

    public static int deleteQuery(Connection connection,String string) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(string);
    }

    public static String getDeleteString(String name){
        return "DELETE FROM Players WHERE name ='"+name+"'" ;
    }

    public static int updateQuery(Connection connection,String string) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(string);
    }

    public static String getUpdateString(String name , int score , int coin , int power ,int bombCount , int x , int y){
        return "UPDATE Players SET score = "+score+", coin="+coin+", power="+power+", bombCount="+bombCount+",x="+x+",y="+y+" WHERE name='"+name+"'" ;
    }

    public static ResultSet selectAllQuery(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT * From Players");
    }

    public static ResultSet selectQuery(Connection connection , String string) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(string);
    }

    public static String getSelectString(String wantedVariable){
        return "SELECT name ,"+ wantedVariable +"FROM Players" ;
    }


}
