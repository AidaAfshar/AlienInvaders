package controller.player;

import com.gilecode.yagson.YaGson;
import com.google.gson.Gson;
import controller.ship.SpaceShip;
import model.dataManagement.DataManager;
import view.screen.ServerPanel;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class Player {

    transient Thread playerThread ;

    String name ;
    transient SpaceShip ship ;
    int score ;
    int coin ;
    int power ;
    int bombCount ;
    int x ;
    int y ;
//    BufferedImage shipImage ;

    transient String data ;


    transient PrintStream printer ;
    transient Scanner scanner ;
    transient ServerPanel panel ;



    public Player(String name ,SpaceShip ship) {
        this.name = name ;
        this.ship = ship ;
        initialize();
    }


    public void initialize() {
        prepareShip();
        setInitialValues();
    }

    public void prepareShip() {
        ship = new SpaceShip();
        ship.setWidth(200);
        ship.setHeight(180) ;
        ship.setDimensions();
        ship.prepareTempTimer();
        ship.setBombCount(bombCount);
    }

    public void setInitialValues(){
        //        shipImage = ship.getImage() ;
        score = 0 ;
        coin = 0 ;
        power = 5 ;
        bombCount = ship.getBombCount() ;
        x = ship.getX() ;
        y = ship.getY() ;

    }

    public void preparePlayer(){
        prepareShip();
        updateValues();
        panel.addPlayer(name);
    }

    public void preparePlayerThread(){
        playerThread = new PlayerThread(this) ;
    }


    public void updateValues(){
        bombCount = ship.getBombCount() ;
        x = ship.getX() ;
        y = ship.getY() ;

        //TODO correct score
        score = coin*3 ;
    }

    public void save(){
        updateValues();
        Gson gson = new Gson() ;
        data = gson.toJson(this) ;
//        YaGson gson = new YaGson() ;
//        data = gson.toJson(this) ;
    }


    @Override
    public String toString() {
        return "{" +
                "name=" + name +
                ", score=" + score +
                ", coin=" + coin +
                ", bombCount=" + bombCount +
                ", x=" + x +
                ", y=" + y +
                '}';
    }


    //getters & setters:


    public String getPlayerName() {
        return name;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getBombCount() {
        return bombCount;
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }

    public SpaceShip getShip() {
        return ship;
    }

    public void setShip(SpaceShip spaceShip) {
        this.ship = spaceShip;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
     this.data = data ;
    }

    public PrintStream getOutputStream() {
        return printer;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.printer = new PrintStream(outputStream);
    }

    public Scanner getInputStream() {
        return scanner;
    }

    public void setInputStream(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    public ServerPanel getPanel() {
        return panel;
    }

    public void setPanel(ServerPanel panel) {
        this.panel = panel;
    }
}
