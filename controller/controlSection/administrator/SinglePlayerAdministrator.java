package controller.controlSection.administrator;

import controller.player.playerExtentions.Player;
import view.screen.ContentPane;

public class SinglePlayerAdministrator extends Administrator{


    public SinglePlayerAdministrator(ContentPane contentPane) {
        super(contentPane);
        this.contentPane = contentPane ;
    }


    //getters & setters :


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        initialize();
    }
}
