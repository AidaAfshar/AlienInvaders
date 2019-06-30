package view.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.imaging.Assets;
import view.imaging.Background;
import view.imaging.ImageLoader;
import view.utilities.Dim;


public class InvitationPanel extends JPanel {

    Background invitationPanelBackground = new Background();
    ContentPane contentPane ;

    JLabel label ;
    JButton button ;

    public InvitationPanel(ContentPane contentPane) {
        super();
        this.contentPane = contentPane ;
        initialize();
    }

    private void initialize() {
        this.setLayout(null);
        this.setBackground(Color.black);
        prepareBackground();
        prepareLabel();
        this.add(label);
        prepareButton();
        this.add(button);
    }

    public void prepareBackground() {
        invitationPanelBackground.setImage(Assets.invitationPanelBackgroundImage);
    }

    private void prepareLabel() {
        label = new JLabel("AILIEN INVADERS");
        label.setFont(new Font("Chiller",Font.BOLD,70));
        label.setForeground(Color.white);
        label.setBounds(Dim.CENTER_X-250,40,600,100);

    }

    private void prepareButton() {
        button = new JButton("Click Here To Start");
        button.setBounds(Dim.CENTER_X-200,Dim.MAX_Y-150,350,50);
        button.setFont(new Font("Footlight MT Light",Font.BOLD,20));;
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.afterInvitationPanel();
            }

        });

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(invitationPanelBackground.getImage(),Dim.CENTER_X-310,Dim.CENTER_Y-320,580,580,null);
//          g.drawImage(invitationPanelBackground.getImage(),-75,140,900,850,null);


    }

}
