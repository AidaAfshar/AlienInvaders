package controller.main.administrator;
import controller.player.Player;
import view.screen.ContentPane;
import java.util.ArrayList;

public class MultiPlayerAdministrator extends Administrator{


    ArrayList<Player> players ;
    Player currentPlayer;


    public MultiPlayerAdministrator(ContentPane contentPane) {
        super(contentPane);
        this.contentPane = contentPane ;
    }


    //getters & setters :


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
        player = currentPlayer ;
        initialize();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
        initialize();
    }


}
