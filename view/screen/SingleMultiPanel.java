package view.screen;

import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SingleMultiPanel extends JPanel{

    Background singleMultiPanelBackground = new Background();
    ContentPane contentPane ;


    JButton singlePlayerButton;
    JButton multiPlayerButton;

    private boolean singlePlayerSelected = false ;
    private boolean multiPlayerSelected = false ;


    public SingleMultiPanel(ContentPane contentPane) {
        super();
        this.contentPane = contentPane ;
        initialize();
    }


    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.black);
        prepareBackground();
        prepareButtons() ;
    }

    public void prepareBackground() {
        singleMultiPanelBackground.setImage(Assets.singleMultiPanelBackgroundImage);
    }


    public void prepareButtons() {
        singlePlayerButton = new JButton("SINGLE PLAYER");
        singlePlayerButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y-120,350,80);
        singlePlayerButton.setFont(new Font("Footlight MT Light",Font.BOLD,40));
        this.add(singlePlayerButton);
        singlePlayerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                singlePlayerSelected = true ;
                contentPane.afterSingleMultiPanel();
            }

        });


        multiPlayerButton = new JButton("MULTI PLAYER");
        multiPlayerButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y+60,350,80);
        multiPlayerButton.setFont(new Font("Footlight MT Light",Font.BOLD,40));
        this.add(multiPlayerButton);
        multiPlayerButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                multiPlayerSelected = true ;
                contentPane.afterSingleMultiPanel();
            }

        });

    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(singleMultiPanelBackground.getImage(),0,0,Dim.MAX_X,Dim.MAX_Y,null);
        g.drawImage(singleMultiPanelBackground.getImage(),220,Dim.CENTER_Y-180,370,370,null);
    }


    public boolean isSinglePlayerSelected() {
        return singlePlayerSelected;
    }

    public boolean isMultiPlayerSelected() {
        return multiPlayerSelected;
    }
}
