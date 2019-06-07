package controller.player;

public class Player {


    String name ;
    int coin ;
    int power ;
    int bombCount ;

    public Player() {
        initialize();
    }


    public Player(String name) {
        this.name = name ;
        initialize();
    }

    public void initialize() {
        coin = 0 ;
        power = 5 ;
        bombCount = 3 ;
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

    public int getBombCount() {
        return bombCount;
    }

    public void setBombCount(int bombCount) {
        this.bombCount = bombCount;
    }
}
