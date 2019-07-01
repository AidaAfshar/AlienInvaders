package view.screen;

import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ServerPanel extends JPanel {

    Background serverPanelBackground = new Background();
    ContentPane contentPane ;

    int playersCount ;

    ArrayList<UserLabel> playersLabel ;


    public ServerPanel(ContentPane contentPane) {
        super();
        this.contentPane = contentPane ;
        this.setLayout(null);
        this.setBackground(Color.black);
    }


    public void initialize() {
        prepareBackground();
        preparePlayersLabels();
        addPlayer(contentPane.getServerPlayer().getPlayerName());
    }

    public void prepareBackground() {
        serverPanelBackground.setImage(Assets.menuPanelBackgroundImage);
    }

    public void preparePlayersLabels() {

        playersLabel = new ArrayList<UserLabel>(playersCount);

        for(int i=0 ; i<playersCount ; i++)
            playersLabel.add(new UserLabel());

        for(int i=0 ; i<playersLabel.size();i++) {
            playersLabel.get(i).setBounds(playersLabel.get(i).x, 250 + i*70,playersLabel.get(i).width,playersLabel.get(i).height );
            this.add(playersLabel.get(i));
        }

    }

    public void updatePlayersList() {
        ArrayList<UserLabel> a = new ArrayList<UserLabel>(5);
        ArrayList<Integer> b = new ArrayList<Integer>();

        for(int i=0 ; i<playersLabel.size();i++) {
            if(playersLabel.get(i).full) {
                a.add(playersLabel.get(i));
            }else {
                b.add(i);
            }
        }
        for(int i=0 ; i<b.size();i++) {
            a.add(playersLabel.get(b.get(i)));
        }
        playersLabel = a ;

        for(int i=0 ; i<playersLabel.size();i++) {
            playersLabel.get(i).setBounds(playersLabel.get(i).x, 250 + i*70,playersLabel.get(i).width,playersLabel.get(i).height );
            if(playersLabel.get(i).name.length()>0)
                playersLabel.get(i).setText((i+1)+ ".   "+ playersLabel.get(i).name);
        }

    }

    public void addPlayer(String playerName) {
        if(playerName!= null && playerName.length()>0) {
            for(int i=0 ; i<playersLabel.size();i++) {
                if(playersLabel.get(i).full==false) {
                    playersLabel.get(i).name = playerName ;
                    playersLabel.get(i).setText((i+1)+ ".   "+  playerName);
                    playersLabel.get(i).full = true ;
                    break ;
                }
            }
        }
    }


    //getters & setters:


    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(serverPanelBackground.getImage(), 0,0,Dim.MAX_X,Dim.MAX_Y,null);
    }

}
