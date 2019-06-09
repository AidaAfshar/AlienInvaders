package view.screen;

import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinPanel extends JPanel{


    Background winPanelBackground = new Background();

    ContentPane contentPane ;

    JLabel winningLabel;
    JLabel scoreLabel ;
    JButton exitButton ;
    JButton newGameButton ;

    int score = 0 ;

    public WinPanel(ContentPane contentPane,int score) {
        super();
        this.contentPane = contentPane ;
        this.score=score ;
        initialize();
    }

    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.black);
        prepareBackground();
        prepareLabel();
        prepareButton();
        prepareTimer();
        timer.start();
    }


    public void prepareBackground() {
        winPanelBackground.setImage(Assets.winPanelBackgroundImage);
    }

    public void prepareLabel(){
        winningLabel = new JLabel("YOU WON !");
        winningLabel.setBounds(200,50,800,200);
        winningLabel.setFont(new Font("Footlight MT Light", Font.BOLD , 80));
        winningLabel.setForeground(Color.WHITE);
        winningLabel.setBackground(Color.WHITE);

        add(winningLabel);

        scoreLabel = new JLabel("Your Score : " + score);
        scoreLabel.setBounds(250,220,800,100);
        scoreLabel.setFont(new Font("Footlight MT Light", Font.BOLD , 50));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBackground(Color.WHITE);

        add(scoreLabel);
    }

    public void prepareButton(){
        exitButton = new JButton("EXIT! ");
        exitButton.setBounds(Dim.CENTER_X+290,Dim.CENTER_Y + 190,300,40);
        exitButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });

        add(exitButton);


        newGameButton = new JButton("NEW GAME");
        newGameButton.setBounds(Dim.CENTER_X+290,Dim.CENTER_Y + 120,300,40);
        newGameButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        newGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.afterWin();
                timer.stop();
            }

        });

        add(newGameButton);

    }





    Timer timer ;

    public void prepareTimer() {
        timer = new Timer(26,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }

        });
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(winPanelBackground.getImage(),0,0,Dim.MAX_X,Dim.MAX_Y,null);
    }

}
