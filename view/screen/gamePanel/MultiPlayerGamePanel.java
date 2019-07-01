package view.screen.gamePanel;

import controller.main.administrator.MultiPlayerAdministrator;
import controller.main.administrator.SinglePlayerAdministrator;
import controller.player.Player;
import view.imaging.Assets;
import view.imaging.Background;
import view.screen.BeamMouseListener;
import view.screen.ContentPane;
import view.screen.MyKeyListener;
import view.screen.MyMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiPlayerGamePanel extends JPanel {

    MultiPlayerAdministrator admin ;
    ContentPane contentPane ;

    Background gamePanelBackground = new Background();

    public MyMouseListener ml ;
    public BeamMouseListener bml ;

    MyKeyListener kl ;
    Timer timer ;

    public MultiPlayerGamePanel(ContentPane contentPane , MultiPlayerAdministrator admin) {
        super();
        this.contentPane = contentPane ;
        this.admin = admin;
        initialize();
    }


    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.gray);
        ml = new MyMouseListener(admin.getShip());
        this.addMouseMotionListener(ml);
        this.addMouseListener(ml);
        bml = new BeamMouseListener(admin.getShip());
        this.addMouseListener(bml);
        this.addMouseMotionListener(bml);
        kl = new MyKeyListener(contentPane);
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
        gamePanelBackground.setImage(Assets.gamePanelBackgroundImage);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gamePanelBackground.draw(g);
        //       admin.getShip().draw(g);
        //       admin.getShip().renderAttack(g);

        for (Player player :admin.getPlayers()){
            player.getShip().draw(g);
            player.getShip().renderAttack(g);
        }
        admin.getGroup().renderGroup(g);

    }


    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    //getters & setters :



}
