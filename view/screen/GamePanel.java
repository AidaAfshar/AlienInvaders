package view.screen;

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

import controller.main.Administrator;
import controller.player.Player;
import controller.ship.SpaceShip;
import view.imaging.Background;
import view.imaging.ImageLoader;
import view.utilities.Dim;

public class GamePanel extends JPanel {

    //attributes:

    Administrator admin ;

    Background background1 = new Background("pictures/backgrounds/background1.png");

    public static MyMouseListener ml = new MyMouseListener();
    public BeamMouseListener bml = new BeamMouseListener();
    MyKeyListener kl = new MyKeyListener();
    static Timer timer ;


    JLabel coinLabel ;
    JLabel powerLabel ;
    JLabel bombLabel ;

    JProgressBar tempBar ;
    JLabel restLabel ;
    JLabel tempLabel ;
    JLabel nameLabel ;

    //methods:



    public GamePanel() {
        super();
        initialize();
    }


    public GamePanel(SpaceShip spaceShip,Player player) {
        super();
        admin = new Administrator(this);
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
        addLabels() ;
        prepareTempTools();
        addTempTools();
    }

    public void prepareTempTools() {
        prepareTempBar() ;
        prepareTempLabel() ;
        prepareRestLabel() ;
    }


    public void prepareTempBar(){
        tempBar = new JProgressBar(0,100);
        tempBar.setOrientation(SwingConstants.HORIZONTAL);
        tempBar.setStringPainted(true);
        tempBar.setBounds(85,20,400,40);
        tempBar.setBackground(Color.darkGray);
        tempBar.setForeground(Color.cyan.darker());
    }

    public void prepareTempLabel(){
        tempLabel = new JLabel("  Temp  ");
        tempLabel.setBounds(0,20,85,40);
        tempLabel.setFont(new Font("Papyrus",Font.BOLD,20));
        tempLabel.setBackground(Color.blue.darker().darker());
        tempLabel.setForeground(Color.white);
        tempLabel.setOpaque(true);
    }

    public void prepareRestLabel(){
        restLabel = new JLabel("                                               ENGINE   IS   RESTING");
        restLabel.setBounds(85,20,400,40);
        restLabel.setBackground(Color.red);
        restLabel.setForeground(Color.black);
        restLabel.setOpaque(true);
    }

    public void addTempTools(){
        add(tempBar);
        add(tempLabel);
    }


    public void prepareLabels() {
        prepareCoinLabel();
        preparePowerLabel();
        prepareBombLabel() ;
        prepareNameLabel() ;

    }

    public void prepareCoinLabel(){
        coinLabel = new JLabel("   Coin : " + admin.getPlayer().getCoin());
        coinLabel.setBackground(Color.magenta.darker());
        coinLabel.setOpaque(true);
        coinLabel.setForeground(Color.white);
        coinLabel.setBounds(0,Dim.MAX_Y-200,80,35);
    }


    public void preparePowerLabel(){
        powerLabel = new JLabel("      Power : " + admin.getPlayer().getPower());
        powerLabel.setBackground(Color.magenta.darker().darker());
        powerLabel.setOpaque(true);
        powerLabel.setForeground(Color.white);
        powerLabel.setBounds(0,Dim.MAX_Y-150,100,35);
    }


    public void prepareBombLabel(){
        bombLabel = new JLabel("         Bomb : " + admin.getPlayer().getBombCount());
        bombLabel.setBackground(Color.magenta.darker().darker().darker());
        bombLabel.setOpaque(true);
        bombLabel.setForeground(Color.white);
        bombLabel.setBounds(0,Dim.MAX_Y-100,120,35);
    }


    public void prepareNameLabel() {
        nameLabel = new JLabel("   Player :     " + admin.getPlayer().getName());
        nameLabel.setBounds(Dim.MAX_X-300,20,300,50);
        nameLabel.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        nameLabel.setForeground(Color.white);
        nameLabel.setBackground(Color.magenta.darker().darker().darker().darker());
        nameLabel.setOpaque(true);

    }


    public void addLabels(){
        add(coinLabel);
        add(powerLabel);
        add(bombLabel);
        add(nameLabel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        background1.draw(g);
        admin.getShip().draw(g);
        admin.getShip().renderAttack(g);
        admin.group.renderGroup(g);
    }


    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }


    //getters & setters :

    public JLabel getCoinLabel() {
        return coinLabel;
    }

    public void setCoinLabel(JLabel coinLabel) {
        this.coinLabel = coinLabel;
    }

    public JLabel getPowerLabel() {
        return powerLabel;
    }

    public void setPowerLabel(JLabel powerLabel) {
        this.powerLabel = powerLabel;
    }

    public JLabel getBombLabel() {
        return bombLabel;
    }

    public void setBombLabel(JLabel bombLabel) {
        this.bombLabel = bombLabel;
    }

    public JProgressBar getTempBar() {
        return tempBar;
    }

    public void setTempBar(JProgressBar tempBar) {
        this.tempBar = tempBar;
    }

    public JLabel getRestLabel() {
        return restLabel;
    }

    public void setRestLabel(JLabel restLabel) {
        this.restLabel = restLabel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }
}