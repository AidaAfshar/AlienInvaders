package view.screen.gamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import controller.controlSection.administrator.SinglePlayerAdministrator;
import controller.ship.SpaceShip;
import view.screen.ContentPane;
import view.utilities.Dim;

public class SinglePlayerGamePanel extends GamePanel {

    //attributes:

    SinglePlayerAdministrator admin ;

    JLabel coinLabel ;
    JLabel powerLabel ;
    JLabel scoreLabel ;
    JLabel bombLabel ;

    JProgressBar tempBar ;
    JLabel restLabel ;
    JLabel tempLabel ;
    JLabel nameLabel ;

    //methods:


    public SinglePlayerGamePanel(ContentPane contentPane , SinglePlayerAdministrator admin) {
        super(contentPane,admin);
        this.contentPane = contentPane ;
        this.admin = admin;
        initialize();
    }


    @Override
    public void prepareTimer() {
        timer = new Timer(10,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                controlTemp();
                updateValues();
            }

        });
    }


    @Override
    public void prepareBackground() {
        super.prepareBackground();
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
        tempBar = new JProgressBar(0,admin.getShip().getMaximumTemp());
        tempBar.setOrientation(SwingConstants.HORIZONTAL);
        tempBar.setStringPainted(true);
        tempBar.setBounds(85,20,4*admin.getShip().getMaximumTemp(),40);
        tempBar.setBackground(Color.darkGray);
        tempBar.setForeground(Color.cyan.darker());

        shipMaxTemp = admin.getShip().getMaximumTemp();
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


    int shipMaxTemp ;

    public void controlTemp() {
        SpaceShip ship = admin.getShip();
        if( shipMaxTemp != ship.getMaximumTemp()){
            shipMaxTemp = ship.getMaximumTemp() ;
            increaseTempInterval();
        }
        if(ship.isTempInSafeRange()) {
            if(ship.getTemperature()>=ship.getMaximumTemp()) {
                ship.setTempInSafeRange(false);
                ship.setTemperature(0);
                remove(getTempBar());
                add(getRestLabel());
                evokeRestTimer();
            }
        }

    }

    public void increaseTempInterval(){
        tempBar.setMaximum(admin.getShip().getMaximumTemp());
        tempBar.setBounds(85,20,4*admin.getShip().getMaximumTemp(),40);
        TempColorThread tempColorChanger = new TempColorThread(this) ;
        tempColorChanger.start();
    }

    public class TempColorThread extends Thread{
        int delay = 100 ;

        SinglePlayerGamePanel panel ;

        public TempColorThread(SinglePlayerGamePanel panel) {
            this.panel = panel ;
        }

        @Override
        public void run() {
            super.run();
            for(int i=0 ; i<5 ; i++){
                tempBar.setBackground(Color.yellow);
                tempBar.setForeground(Color.orange);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tempBar.setBackground(Color.darkGray);
                tempBar.setForeground(Color.cyan.darker());
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            currentThread().interrupt();
        }
    }

    java.util.Timer restTimer ;


    public void evokeRestTimer() {
        restTimer = new java.util.Timer();
        restTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                remove(getRestLabel());
                add(getTempBar());
                admin.getShip().setTempInSafeRange(true);
            }

        },4000);
    }



    public void prepareLabels() {
        prepareCoinLabel();
        preparePowerLabel();
        prepareBombLabel() ;
        prepareScoreLabel();
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

    public void prepareScoreLabel() {
        scoreLabel = new JLabel("   Score :     " + admin.getPlayer().getScore());
        scoreLabel.setBounds(Dim.MAX_X-300 + 40,85,300,50);
        scoreLabel.setFont(new Font("Footlight MT Light",Font.BOLD,25));
        scoreLabel.setForeground(Color.white);
        scoreLabel.setBackground(Color.blue.darker().darker());
        scoreLabel.setOpaque(true);
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
        add(scoreLabel) ;
        add(nameLabel);
    }

    public void updateValues() {
        getTempBar().setValue(admin.getShip().getTemperature());
        getBombLabel().setText("         Bomb : " + admin.getShip().getBombCount());
        getPowerLabel().setText("      Power : " + admin.getPlayer().getPower());
        getCoinLabel().setText("   Coin : " + admin.getPlayer().getCoin());
        getScoreLabel().setText("   Score :     " + admin.getPlayer().getScore());
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        admin.getShip().draw(g);
        admin.getShip().renderAttack(g);
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

    public JLabel getScoreLabel() {
        return scoreLabel;
    }
}