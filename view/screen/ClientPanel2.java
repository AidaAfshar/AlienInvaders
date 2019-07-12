package view.screen;

import controller.player.Player;
import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientPanel2 extends JPanel {

    Background clientPanelBackground = new Background();
    ContentPane contentPane ;

    int playersCount = 5 ;

    Player clientPlayer ;
    ArrayList<Player> otherPlayers ;

    ArrayList<UserLabel> playersLabels;
    ArrayList<UserLabel> scoresLabels;

    JButton playerButton ;
    JButton spectatorButton ;


    public ClientPanel2(ContentPane contentPane) {
        super();
        this.contentPane = contentPane ;
        initialize();
    }


    public ClientPanel2(ContentPane contentPane , Player clientPlayer , ArrayList<Player> otherPlayers) {
        super();
        this.contentPane = contentPane ;
        this.clientPlayer = clientPlayer ;
        this.otherPlayers = otherPlayers ;
        initialize();
    }


    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.black);
        this.playersCount = otherPlayers.size()+1 ;
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
        scoresLabels = new ArrayList<>(playersCount) ;

        for(int i=0 ; i<playersCount ; i++) {
            playersLabels.add(new UserLabel());
            scoresLabels.add(new UserLabel());
        }

        for(int i = 0; i<playersCount ; i++) {
            playersLabels.get(i).setBounds(playersLabels.get(i).x, 250 + i*70, playersLabels.get(i).width, playersLabels.get(i).height );
            this.add(playersLabels.get(i));

            scoresLabels.get(i).setBounds(scoresLabels.get(i).x + 700, 250 + i*70, scoresLabels.get(i).width, scoresLabels.get(i).height );
            this.add(scoresLabels.get(i));
        }

        addPlayer(clientPlayer.getName() + "(You)" , clientPlayer.getScore());

        for(int i=1 ; i<playersLabels.size() ;i++){
            addPlayer(otherPlayers.get(i-1).getName(), otherPlayers.get(i-1).getScore());

        }
    }

    public void updatePlayersList() {


        ArrayList<UserLabel> a = new ArrayList<UserLabel>(playersCount);
        ArrayList<Integer> b = new ArrayList<Integer>();

        for(int i = 0; i< playersLabels.size(); i++) {
            if(playersLabels.get(i).full) {
                a.add(playersLabels.get(i));
            }else {
                b.add(i);
            }
        }
        for(int i=0 ; i<b.size();i++) {
            a.add(playersLabels.get(b.get(i)));
        }
        playersLabels = a ;

        for(int i = 0; i< playersLabels.size(); i++) {
            playersLabels.get(i).setBounds(playersLabels.get(i).x, 250 + i*70, playersLabels.get(i).width, playersLabels.get(i).height );
            if(playersLabels.get(i).name.length()>0)
                playersLabels.get(i).setText((i+1)+ ".   "+ playersLabels.get(i).name);
        }

    }

    public void addPlayer(String playerName) {
        if(playerName!= null && playerName.length()>0) {
            for(int i = 0; i< playersLabels.size(); i++) {
                if(playersLabels.get(i).full==false) {
                    playersLabels.get(i).name = playerName ;
                    playersLabels.get(i).setText((i+1)+ ".   "+  playerName);
                    playersLabels.get(i).full = true ;
                    break ;
                }
            }
        }

        updatePlayersList();
    }


    public void addPlayer(String playerName , int playerScore) {

        if(playerName!= null && playerName.length()>0) {
            for(int i = 0; i< playersLabels.size(); i++) {
                if(playersLabels.get(i).full==false) {
                    playersLabels.get(i).name = playerName ;
                    playersLabels.get(i).setText((i+1)+ ".   "+  playerName);
                    scoresLabels.get(i).setText(String.valueOf(playerScore));
                    playersLabels.get(i).full = true ;
                    break ;
                }
            }
        }



        updatePlayersList();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(clientPanelBackground.getImage(), 0,0, Dim.MAX_X,Dim.MAX_Y,null);

    }

    //getters & setters:


    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    public void setOtherPlayers(ArrayList<Player> otherPlayers) {
        this.otherPlayers = otherPlayers;
    }
}
