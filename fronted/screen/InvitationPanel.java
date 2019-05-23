package fronted.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fronted.imaging.Background;
import fronted.imaging.ImageLoader;
import fronted.utilities.Dim;


public class InvitationPanel extends JPanel {

    Background background4 = new Background("pictures/backgrounds/background4.png");
    JLabel label ;
    JButton button ;
    boolean flag = false ;

    public InvitationPanel() {
        super();
        initialize();
    }

    private void initialize() {
        this.setLayout(null);
        this.setBackground(Color.magenta);
        prepareBackground();
        prepareLabel();
        this.add(label);
        prepareButton();
        this.add(button);
    }

    public void prepareBackground() {
        background4.setImage(ImageLoader.Load(background4.getAddress()));
    }

    private void prepareLabel() {
        label = new JLabel("AILIEN INVADERS");
        label.setFont(new Font("Chiller",Font.BOLD,70));
        label.setForeground(Color.white);
        label.setBounds(Dim.CENTER_X-250,40,600,100);

    }

    private void prepareButton() {
        button = new JButton("Click Here To Start");
        button.setBounds(Dim.CENTER_X-200,Dim.MAX_Y-150,350,50);
        button.setFont(new Font("Footlight MT Light",Font.BOLD,20));;
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                flag = true ;
            }

        });

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background4.getImage(),0,0,Dim.MAX_X,Dim.MAX_Y,null);
    }

}
