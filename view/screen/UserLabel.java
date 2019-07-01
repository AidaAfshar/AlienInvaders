package view.screen;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class UserLabel extends JLabel {


    String name ="";
    int x = 150 ;
    int y ;
    int width = 600 ;
    int height = 50 ;
    boolean chosen = false ;
    boolean full = false ;

    Color color = Color.white;
    Color focusColor = Color.green.darker();
    Color chosenColor = Color.red;

    UserLabelListener ul ;

    public UserLabel() {
        super("");
        initialize();
    }


    public void initialize() {
        ul = new UserLabelListener(this);
        this.addMouseListener(ul);
        this.addMouseMotionListener(ul);
        this.setForeground(color);
        this.setFont(new Font("Chiller",Font.BOLD,50));
    }
}