package fronted.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import engine.main.Administrator;
import engine.player.Player;
import engine.ship.SpaceShip;
import fronted.imaging.Background;
import fronted.imaging.ImageLoader;
import fronted.utilities.Dim;

public class GamePanel extends JPanel {

    //attributes:

    Administrator admin ;

    Background background1 = new Background("pictures/backgrounds/background1.png");

    public static MyMouseListener ml = new MyMouseListener();
    public BeamMouseListener bml = new BeamMouseListener();
    MyKeyListener kl = new MyKeyListener();
    static Timer timer ;


    public JLabel coinLabel ;
    public JLabel powerLabel ;
    public JLabel bombLabel ;

    public JProgressBar tempBar ;
    public JLabel restLabel ;
    JLabel tempLabel ;
    JLabel nameLabel ;

    //methods:



    public GamePanel() {
        super();
        initialize();
    }


    public GamePanel(SpaceShip spaceShip,Player player) {
        super();
        admin = new Administrator(this,spaceShip,player);
        initialize();
    }


    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.gray);
        this.addMouseMotionListener(ml);
        this.addMouseListener(ml);
        this.addMouseListener(bml);
        this.addKeyListener(kl);
        this.setFocusable(true);
        prepareBackground();
        prepareTimer();
        timer.start();
    }


    public void prepareTimer() {
        timer = new Timer(10,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }

        });
    }

    public void prepareBackground() {
        background1.setImage(ImageLoader.Load(background1.getAddress()));
        prepareLabels();
        prepareTempTools();
        prepareNameLabel();
    }


    public void prepareTempTools() {
        tempBar = new JProgressBar(0,100);
        tempBar.setOrientation(SwingConstants.HORIZONTAL);
        tempBar.setStringPainted(true);
        tempBar.setBounds(85,20,400,40);
        tempBar.setBackground(Color.darkGray);
        tempBar.setForeground(Color.cyan.darker());

        this.add(tempBar);


        tempLabel = new JLabel("  Temp  ");
        tempLabel.setBounds(0,20,85,40);
        tempLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        tempLabel.setBackground(Color.blue.darker().darker());
        tempLabel.setForeground(Color.white);
        tempLabel.setOpaque(true);

        this.add(tempLabel);

        restLabel = new JLabel("                                               ENGINE   IS   RESTING");
        restLabel.setBounds(85,20,400,40);
        restLabel.setBackground(Color.red);
        restLabel.setForeground(Color.black);
        restLabel.setOpaque(true);
    }

    public void prepareLabels() {
        coinLabel = new JLabel("   Coin : " + admin.ship.coin);
        powerLabel = new JLabel("      Power : " + admin.ship.power);
        bombLabel = new JLabel("         Bomb : " + admin.ship.bombCount);

        coinLabel.setBackground(Color.magenta.darker());
        powerLabel.setBackground(Color.magenta.darker().darker());
        bombLabel.setBackground(Color.magenta.darker().darker().darker());

        coinLabel.setOpaque(true);
        powerLabel.setOpaque(true);
        bombLabel.setOpaque(true);

        coinLabel.setForeground(Color.white);
        powerLabel.setForeground(Color.white);
        bombLabel.setForeground(Color.white);

        coinLabel.setBounds(0,Dim.MAX_Y-200,80,35);
        powerLabel.setBounds(0,Dim.MAX_Y-150,100,35);
        bombLabel.setBounds(0,Dim.MAX_Y-100,120,35);

        this.add(coinLabel);
        this.add(bombLabel);
        this.add(powerLabel);


    }

    public void prepareNameLabel() {
        nameLabel = new JLabel("   Player :     " + admin.player.name);
        nameLabel.setBounds(Dim.MAX_X-300,20,300,50);
        nameLabel.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        nameLabel.setForeground(Color.white);
        nameLabel.setBackground(Color.magenta.darker().darker().darker().darker());
        nameLabel.setOpaque(true);
        this.add(nameLabel);
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        background1.draw(g);
        admin.ship.draw(g);
        admin.ship.shipAttack.renderAttack(g);
        admin.rect.renderGroup(g);
    }



    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }


}