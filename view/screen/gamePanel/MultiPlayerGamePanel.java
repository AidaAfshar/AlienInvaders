package view.screen.gamePanel;

import controller.main.administrator.MultiPlayerAdministrator;
import controller.player.playerExtentions.Player;
import view.screen.ContentPane;
import java.awt.*;

public class MultiPlayerGamePanel extends GamePanel {

    MultiPlayerAdministrator admin ;

    public MultiPlayerGamePanel(ContentPane contentPane , MultiPlayerAdministrator admin) {
        super(contentPane,admin);
        this.contentPane = contentPane ;
        this.admin = admin;
        initialize();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Player player :admin.getPlayers()){
            player.getShip().draw(g);
            player.getShip().renderAttack(g);
        }

    }

}
