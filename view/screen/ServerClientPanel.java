package view.screen;

import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerClientPanel extends JPanel {

    Background serverClientPanelBackground = new Background();
    ContentPane contentPane ;


    JButton serverButton;
    JButton clientButton;
    JLabel label ;

    private boolean serverSelected = false ;
    private boolean clientSelected = false ;


    public ServerClientPanel(ContentPane contentPane) {
        super();
        this.contentPane = contentPane ;
        initialize();
    }


    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.black);
        prepareBackground();
        prepareLabel();
        prepareButtons() ;
    }

    public void prepareBackground() {
        serverClientPanelBackground.setImage(Assets.rankingPanelPanelBackgroundImage);
    }

    public void prepareLabel(){
        label = new JLabel("I want to be :") ;
        label.setBounds(Dim.CENTER_X-200,Dim.CENTER_Y-300,600,80);
        label.setForeground(Color.white);
        label.setFont(new Font("Segoe Print", Font.BOLD , 60));
        add(label);
    }


    public void prepareButtons() {
        serverButton = new JButton("SERVER");
        serverButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y-100,350,80);
        serverButton.setFont(new Font("Footlight MT Light",Font.BOLD,40));
        add(serverButton);
        serverButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                serverSelected = true ;
                contentPane.afterServerClientPanel();
            }

        });


        clientButton = new JButton("CLIENT");
        clientButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y+100,350,80);
        clientButton.setFont(new Font("Footlight MT Light",Font.BOLD,40));
        this.add(clientButton);
        clientButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clientSelected = true ;
                contentPane.afterServerClientPanel();
            }

        });

    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(serverClientPanelBackground.getImage(),0,0,Dim.MAX_X,Dim.MAX_Y,null);
    }


    public boolean isServerSelected() {
        return serverSelected;
    }

    public boolean isClientSelected() {
        return clientSelected;
    }
}
