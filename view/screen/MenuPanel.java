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

import view.animations.Skeleton;
import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;

public class MenuPanel extends JPanel {

    Background menuPanelBackground = new Background();

    ContentPane contentPane ;

    private boolean newGameSelected = false ;
    private boolean resumeGameSelected = false ;


    JLabel welcomeLabel ;
    JButton resumeButton ;
    JButton newGameButton ;
    JButton rankingsButton ;
    JButton exitButton ;
    JButton infoButton ;

    Timer timer ;
    Skeleton skeleton = new Skeleton();

    public MenuPanel(ContentPane contentPane) {
        super();
        this.contentPane = contentPane ;
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
        menuPanelBackground.setImage(Assets.menuPanelBackgroundImage);
    }

    public void prepareWelcomeLabel() {
        welcomeLabel = new JLabel("Welcome  " + contentPane.getPlayer().getPlayerName() + "  !");
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
                resumeGameSelected = true ;
                contentPane.afterMenuPanel();
                timer.stop();
            }

        });

        newGameButton = new JButton("NEW GAME");
        newGameButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y+190,300,50);
        newGameButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        newGameButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                newGameSelected = true ;
                contentPane.afterMenuPanel();
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
                System.exit(0);
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
        g.drawImage(menuPanelBackground.getImage(),0,0,Dim.MAX_X,Dim.MAX_Y,null);
        skeleton.renderMovement(g, skeleton.j%25);
    }


    public boolean isNewGameSelected() {
        return newGameSelected;
    }

    public boolean isResumeGameSelected() {
        return resumeGameSelected;
    }
}
