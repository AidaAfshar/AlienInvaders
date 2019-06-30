package view.screen;

import view.imaging.Assets;
import view.imaging.Background;

import javax.swing.*;
import java.awt.*;

public class ServerPanel extends JPanel {

    Background serverPanelBackground = new Background();
    ContentPane contentPane ;


    public ServerPanel(ContentPane contentPane) {
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
        serverPanelBackground.setImage(Assets.menuPanelBackgroundImage);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

    }


}
