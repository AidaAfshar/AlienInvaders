package view.screen;

import controller.player.Player;
import controller.player.PlayerState;
import controller.player.PlayerType;
import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientPanel extends JPanel {

    Background clientPanelBackground = new Background();
    ContentPane contentPane ;

    int playersCount = 8 ;

    Player clientPlayer ;
    ArrayList<Player> otherPlayers ;


    ArrayList<PlayerLabel> playersLabels;
    ArrayList<PlayerLabel> scoresLabels;


    JButton playerButton ;
    JButton spectatorButton ;


    public ClientPanel(ContentPane contentPane,Player clientPlayer){
        this.contentPane = contentPane ;
        this.clientPlayer = clientPlayer ;
        initialize();
    }


    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.black);
        prepareBackground();
        preparePlayersLabels();
        prepareButtons() ;
    }

    public void prepareBackground() {
        clientPanelBackground.setImage(Assets.menuPanelBackgroundImage);
    }

    public void prepareButtons(){
        playerButton = new JButton("Enter as Player") ;
        playerButton.setBounds(Dim.CENTER_X - 600 , Dim.MAX_Y-200 , 500 , 60);
        playerButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        add(playerButton) ;
        playerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.afterClientPanel("player");
            }
        });

        spectatorButton = new JButton("Enter as Spectator") ;
        spectatorButton.setBounds(Dim.CENTER_X + 100 , Dim.MAX_Y-200 , 500 , 60);
        spectatorButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        add(spectatorButton) ;
        spectatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.afterClientPanel("spectator");
            }
        });
    }


    public void preparePlayersLabels() {
        playersLabels = new ArrayList<>(playersCount);
        scoresLabels = new ArrayList<>(playersCount);

        for (int i = 0; i < playersCount; i++) {
            playersLabels.add(new PlayerLabel());
            scoresLabels.add(new PlayerLabel());
        }

        for (int i = 0; i < playersCount; i++) {
            playersLabels.get(i).setBounds(playersLabels.get(i).x, 100 + i * 70, playersLabels.get(i).width, playersLabels.get(i).height);
            this.add(playersLabels.get(i));

            scoresLabels.get(i).setBounds(scoresLabels.get(i).x + 700, 100 + i * 70, 200, scoresLabels.get(i).height);
            this.add(scoresLabels.get(i));
        }

        addPlayer(clientPlayer);

    }


    public void addPlayer(String playerName , int playerScore) {
            for (int i = 0; i < playersLabels.size(); i++) {
                if (! playersLabels.get(i).isFull()) {
                    playersLabels.get(i).setName(playerName);
                    playersLabels.get(i).setText((i + 1) + ".   " + playerName);
                    scoresLabels.get(i).setText(String.valueOf(playerScore));
                    playersLabels.get(i).setFull(true);
                    break;
                }
            }
    }

    public void addPlayer(Player player) {
        for (int i = 0; i < playersLabels.size(); i++) {
            if (! playersLabels.get(i).isFull()) {
                playersLabels.get(i).setName(player.getName());
                playersLabels.get(i).setText((i + 1) + ".   " + player.getName());
                scoresLabels.get(i).setText(String.valueOf(player.getScore()));
                playersLabels.get(i).setFull(true);
                break;
            }
        }
    }


    public void updatePlayersList(Player player){
        otherPlayers.add(player) ;
        playersCount++ ;
        addPlayer(player);
    }

    public void updateScore(Player player){

        for(int i=0 ; i<playersLabels.size() ; i++){
            if(player.getName().equals(playersLabels.get(i).getName())){
                scoresLabels.get(i).setText(String.valueOf(player.getScore()));
            }
        }

    }

}