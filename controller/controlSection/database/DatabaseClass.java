package controller.controlSection.database;

import java.sql.*;

public class DatabaseClass {

    public static Connection getConnection(String dbmsHost, String user, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
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


    public static int insertQuery(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(clientQuery ("new Player",0,0,0,0,0));
    }


    public static String clientQuery(String name , int score , int coin , int bombCount , int x , int y){
        return "insert into Players (name, score, coin , bomb count , x , y) values("+name+","+score+","+coin+","+bombCount +","+x+","+y+")" ;
    }

    public static int deleteQuery(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate("delete from Players where name like 'New Player%'");
    }

}
