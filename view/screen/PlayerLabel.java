package view.screen;

import javax.swing.*;
import java.awt.*;

public class PlayerLabel extends JLabel {

    String name ="";
    int x = 150 ;
    int y ;
    int width = 600 ;
    int height = 50 ;
    boolean full = false ;

    Color color = Color.white;


    public PlayerLabel() {
        super("");
        initialize();
    }

    public void initialize() {
        this.setForeground(color);
        this.setFont(new Font("Chiller",Font.BOLD,50));
    }


    //getters & setters :


    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
