package view.screen;

import controller.player.playerExtentions.Player;
import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RankingPanel extends JPanel {

    Background rankingPanelBackground = new Background();

    ContentPane contentPane ;

    JButton backButton ;

    ArrayList<Player> savedPlayers ;
    ArrayList<PlayerLabel> playersLabels;
    ArrayList<PlayerLabel> scoresLabels;

    int playersCount = 8 ;

    public RankingPanel(ContentPane contentPane,ArrayList<Player> savedPlayers) {
        super();
        this.contentPane = contentPane ;
        this.savedPlayers = savedPlayers ;
        initialize();
    }

    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.green);
        prepareBackground();
        prepareButton() ;
        prepareLabels() ;
        prepareList() ;
    }

    void prepareBackground() {
        rankingPanelBackground.setImage(Assets.rankingPanelPanelBackgroundImage);
    }

    void prepareButton(){
        backButton = new JButton("back") ;
        backButton.setBounds(50+20, Dim.CENTER_Y+290,150,40);
        backButton.setFont(new Font("Footlight MT Light",Font.BOLD,20));
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.closeRankingPanel();
            }

        });
    }

    void prepareList(){
        for(Player player : savedPlayers)
            addPlayer(player);
    }

    public void prepareLabels() {
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

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(rankingPanelBackground.getImage(),0,0,Dim.MAX_X,Dim.MAX_Y,null) ;
    }

}
