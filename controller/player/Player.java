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

//    transient Thread playerThread ;

    String name ;
    transient SpaceShip ship ;
    int score ;
    int coin ;
    int power ;
    int bombCount ;
    int x ;
    int y ;

    PlayerState state = PlayerState.PREGAME ;
    PlayerType type = PlayerType.UNDECLARED ;

    transient String data ;

    transient PrintStream printer ;
    transient Scanner scanner ;

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
    }

//    public void preparePlayerThread(){
//        playerThread = new PlayerThread(this) ;
//    }


    public void updateValues(){
        bombCount = ship.getBombCount() ;
        x = ship.getX() ;
        y = ship.getY() ;

        //TODO correct score
        score = coin*3 ;
    }

    public void update(Player player){
        this.score = player.getScore();
        this.coin = player.getCoin() ;
        this.bombCount = player.getBombCount() ;
        this.x = player.getX() ;
        this.y = player.getY() ;
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

    @Override
    public boolean equals(Object object) {
        if(object instanceof Player){
            Player player = (Player) object ;
            if(player.getName().equals(this.getName()))
                return true ;
            else
                return false ;
        }
        return false ;
    }

    //getters & setters:


    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public boolean isInGame(){
        if(this.state == PlayerState.INGAME) {
            return true;
        }
        return false ;
    }
}
