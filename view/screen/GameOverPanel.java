package view.screen;

import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverPanel extends JPanel {

    Background gameOverPanelBackground = new Background();

    ContentPane contentPane ;

    JLabel gameOverLabel ;
    JButton exitButton ;
    JButton newGameButton ;

    public GameOverPanel(ContentPane contentPane) {
        super();
        this.contentPane = contentPane ;
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
        gameOverPanelBackground.setImage(Assets.gameOverPanelBackgroundImage);
    }

    public void prepareLabel(){
        gameOverLabel = new JLabel("GAME OVER!");
        gameOverLabel.setBounds(Dim.CENTER_X+20,Dim.CENTER_Y-130,800,200);
        gameOverLabel.setFont(new Font("Chiller", Font.BOLD , 100));
        gameOverLabel.setForeground(Color.red);
        gameOverLabel.setBackground(Color.red);
        gameOverLabel.setForeground(Color.red);
        gameOverLabel.setBackground(Color.red);

        add(gameOverLabel);
    }

    public void prepareButton(){
        exitButton = new JButton("EXIT! ");
        exitButton.setBounds(Dim.CENTER_X+90,Dim.CENTER_Y + 190,300,40);
        exitButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });

        add(exitButton);


        newGameButton = new JButton("NEW GAME");
        newGameButton.setBounds(Dim.CENTER_X+90,Dim.CENTER_Y + 120,300,40);
        newGameButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        newGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.afterGameOver();
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
        g.drawImage(gameOverPanelBackground.getImage(),100,Dim.MAX_Y-600,500,500,null);
    }

}
