package view.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.player.Player;
import view.animations.Skeleton;
import view.imaging.Background;
import view.imaging.ImageLoader;
import view.utilities.Dim;

public class MenuPanel extends JPanel {

    Background background5 = new Background("pictures/backgrounds/background5.png");
    Player player ;

    boolean flag = false ;
    boolean newGameSelected = false ;
    boolean resumeGameSelected = false ;

    ContentPane contentPane ;

    JLabel welcomeLabel ;
    JButton resumeButton ;
    JButton newGameButton ;
    JButton rankingsButton ;
    JButton exitButton ;
    JButton infoButton ;

    Timer timer ;
    Skeleton skeleton = new Skeleton();

    public MenuPanel(Player player) {
        super();
        this.player = player ;
        initialize();
    }

    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.green);
        prepareBackground();
        prepareWelcomeLabel();
        this.add(welcomeLabel);
        prepareButtons() ;
        this.add(resumeButton);
        this.add(newGameButton);
        this.add(rankingsButton);
        this.add(infoButton);
        this.add(exitButton);

        prepareTimer();
        timer.start();
    }


    public void prepareBackground() {
        background5.setImage(ImageLoader.Load(background5.getAddress()));
    }

    public void prepareWelcomeLabel() {
        welcomeLabel = new JLabel("Welcome  " + player.name + "  !");
        welcomeLabel.setBounds(Dim.CENTER_X-250,Dim.CENTER_Y-100,700,200);
        welcomeLabel.setForeground(Color.red);
        welcomeLabel.setFont(new Font("Chiller", Font.BOLD , 80));
    }


    public void prepareButtons() {
        resumeButton = new JButton("RESUME");
        resumeButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y+100,300,50);
        resumeButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        resumeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                flag = true ;
                resumeGameSelected = true ;
                timer.stop();
            }

        });

        newGameButton = new JButton("NEW GAME");
        newGameButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y+190,300,50);
        newGameButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        newGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                flag = true ;
                newGameSelected = true ;
                timer.stop();
            }

        });

        rankingsButton = new JButton("RANKINGS");
        rankingsButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y+280,300,50);
        rankingsButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));

        exitButton = new JButton("exit");
        exitButton.setBounds(50+20,Dim.CENTER_Y+290,150,40);
        exitButton.setFont(new Font("Footlight MT Light",Font.BOLD,20));
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }

        });

        infoButton = new JButton("about us!");
        infoButton.setBounds(Dim.MAX_X-400+150,Dim.CENTER_Y+290,150,40);
        infoButton.setFont(new Font("Footlight MT Light",Font.BOLD,20));

    }


    public void prepareTimer() {
        timer = new Timer(26,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                skeleton.j++ ;
            }

        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background5.getImage(),0,0,Dim.MAX_X,Dim.MAX_Y,null);
        skeleton.renderMovement(g, skeleton.j%25);
    }



}
