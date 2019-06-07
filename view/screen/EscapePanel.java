package view.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.imaging.Background;
import view.imaging.ImageLoader;
import view.utilities.Dim;


public class EscapePanel extends JPanel {

    Background background1 = new Background("pictures/backgrounds/background1.png");
    ContentPane contentPane ;

    JButton resumeButton;
    JButton exitButton;

//    MyKeyListener kl ;


    public EscapePanel(ContentPane contentPane) {
        super();
        this.contentPane = contentPane ;
        initialize();
    }


    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.red);
//        kl = new MyKeyListener(contentPane);
//        this.addKeyListener(kl);
        prepareBackground();
        prepareButtons() ;
    }

    public void prepareBackground() {
        background1.setImage(ImageLoader.Load(background1.getAddress()));
    }


    public void prepareButtons() {
        resumeButton = new JButton("RESUME");
        resumeButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y-100,350,80);
        resumeButton.setFont(new Font("Footlight MT Light",Font.BOLD,40));
        this.add(resumeButton);
        resumeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.resumeGameFromEscapePanel();
            }

        });


        exitButton = new JButton("EXIT");
        exitButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y+100,350,80);
        exitButton.setFont(new Font("Footlight MT Light",Font.BOLD,40));
        this.add(exitButton);
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.saveState();
                contentPane.fileManager.close();
                System.exit(0);
            }

        });

    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background1.getImage(),0,0,Dim.MAX_X,Dim.MAX_Y,null);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

}
