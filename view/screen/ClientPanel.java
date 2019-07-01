package view.screen;

import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;

public class ClientPanel extends JPanel {

    Background clientPanelBackground = new Background();
    ContentPane contentPane ;


    public ClientPanel(ContentPane contentPane) {
        super();
        this.contentPane = contentPane ;
        initialize();
    }


    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.black);
        prepareBackground();

    }

    public void prepareBackground() {
        clientPanelBackground.setImage(Assets.menuPanelBackgroundImage);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(clientPanelBackground.getImage(), 0,0, Dim.MAX_X,Dim.MAX_Y,null);

    }

}
