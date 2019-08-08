package view.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.dataManagement.DataManagement;
import model.database.DatabaseManager;
import controller.player.playerExtentions.Player;
import controller.ship.SpaceShip;
import model.fileManagement.FileManager;
import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;


public class UsersPanel extends JPanel {

    Background usersPanelBackground = new Background();
    ContentPane contentPane ;

    ArrayList<UserLabel> playersLabel = new ArrayList<UserLabel>(5);
    ArrayList<Player> savedPlayers ;


    String playerName ;

    JButton deleteButton ;
    JButton addButton ;
    JButton startButton ;
    JOptionPane optionPane ;
    JLabel label ;

    FileManager fileManager ;
    DatabaseManager databaseManager ;

    public UsersPanel(ContentPane contentPane){
        super();
        this.contentPane = contentPane ;
        initialize();
    }

    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.black);
        prepareBackground();
//        prepareFileManager() ;
//        prepareDatabaseManager() ;
        prepareDataManagement() ;
        prepareLabel();
        prepareUserLabels();
        prepareButtons();
        this.add(deleteButton);
        this.add(addButton);
        this.add(startButton);
    }

    void prepareBackground() {
        usersPanelBackground.setImage(Assets.usersPanelBackgroundImage);
    }


    void prepareDataManagement(){
        if(contentPane.getDataManagement().equals(DataManagement.VIA_FILE))
            prepareFileManager();
        if(contentPane.getDataManagement().equals(DataManagement.VIA_DATABASE))
            prepareDatabaseManager();
    }

    void prepareFileManager(){
        fileManager = contentPane.getFileManager() ;
        savedPlayers = fileManager.getSavedList () ;
        normalizeSavedPlayers();
    }

    void prepareDatabaseManager(){
        databaseManager = contentPane.getDatabaseManager() ;
        savedPlayers = databaseManager.getPlayersArrayList () ;
        normalizeSavedPlayers();
    }

    void normalizeSavedPlayers(){
        ArrayList<Player> temp = new ArrayList<>() ;
        for(int i=savedPlayers.size()-1 ; i>=0 ; i--){
            Player player = savedPlayers.get(i) ;
            if(! alreadyExists(player,temp)){
                temp.add(player);
            }
        }

        savedPlayers = temp ;
    }

    static boolean alreadyExists(Player player,ArrayList<Player> temp){
        for(int i=0 ; i<temp.size() ; i++){
            Player tempPlayer = temp.get(i) ;
            if(tempPlayer.getName().equals(player.getName())){
                return true ;
            }
        }
        return false ;
    }

    public void prepareLabel() {
        label = new JLabel(" U S E R S !");
        label.setBounds(Dim.CENTER_X-200,20,500,200);
        label.setFont(new Font("Chiller",Font.BOLD,80));
        label.setForeground(Color.white);
        this.add(label);
    }


    public void prepareUserLabels() {
        for(int i=0 ; i<5 ; i++)
            playersLabel.add(new UserLabel());

        for(int i=0 ; i<playersLabel.size();i++) {
            playersLabel.get(i).setBounds(playersLabel.get(i).x, 250 + i*70,playersLabel.get(i).width,playersLabel.get(i).height );
            this.add(playersLabel.get(i));
        }

        addSavedPlayersToPanel();
    }


    public void prepareButtons() {

        deleteButton = new JButton("DELETE USER");
        deleteButton.setBounds(Dim.MAX_X-500+10,Dim.MAX_Y-150,200,30);
        deleteButton.setFont(new Font("Footlight MT Light",Font.BOLD,20));
        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                removeUser(JOptionPane.showInputDialog("Enter users name"));

            }

        });

        addButton = new JButton("ADD USER");
        addButton.setBounds(Dim.CENTER_X-150+10,Dim.MAX_Y-150,200,30);
        addButton.setFont(new Font("Footlight MT Light",Font.BOLD,20));
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                String name = JOptionPane.showInputDialog("Enter users name") ;
                addUser(name);
            }

        });

        startButton = new JButton("START !");
        startButton.setBounds(200+10,Dim.MAX_Y-150,200,30);
        startButton.setFont(new Font("Footlight MT Light",Font.BOLD,20));
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                prepareNewPlayer();
                if(playerAlreadyExists()){
                       Player player = getExistingPlayer(playerName) ;
                        contentPane.setPlayer(player);
                }else{
                    Player newPlayer = new Player(playerName, new SpaceShip());
                    if(contentPane.getDataManagement()==DataManagement.VIA_DATABASE)
                    databaseManager.addPlayerToDatabase(newPlayer);
                    contentPane.setPlayer(newPlayer);
                }
                contentPane.afterUsersPanel();
            }

        });

    }

    void prepareNewPlayer() {
        for(int i=0 ; i<playersLabel.size();i++) {
            if(playersLabel.get(i).chosen) {
                playerName = playersLabel.get(i).name ;
            }
        }
    }

    boolean playerAlreadyExists(){

        for(int i=savedPlayers.size()-1 ; i>=0 ; i--){
            if(savedPlayers.get(i).getName().equals(playerName))
                return true ;
        }

        return false ;
    }



    Player getExistingPlayer(String playerName){
        for(int i=savedPlayers.size()-1 ; i>=0 ; i--){
            if(savedPlayers.get(i).getName().equals(playerName))
                return savedPlayers.get(i) ;
        }
        return null ;
    }

    public void removeUser(String showInputDialog) {

        if(showInputDialog!= null && showInputDialog.length()>0) {
            for(int i=0 ; i<playersLabel.size();i++) {
                if(playersLabel.get(i).name.equals(showInputDialog)) {
                    playersLabel.get(i).setText("");
                    playersLabel.get(i).name = "";
                    playersLabel.get(i).full = false ;
                    break ;
                }
            }
        }

        updatePlayersList();


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


    public void addUser(String showInputDialog) {
        if(showInputDialog!= null && showInputDialog.length()>0) {
            for(int i=0 ; i<playersLabel.size();i++) {
                if(playersLabel.get(i).full==false) {
                    playersLabel.get(i).name = showInputDialog ;
                    playersLabel.get(i).setText((i+1)+ ".   "+  showInputDialog);
                    playersLabel.get(i).full = true ;
                    break ;
                }
            }
        }
    }


    void addSavedPlayersToPanel(){
        int size = savedPlayers.size () ;
        if(size != 0){
            for(Player player: savedPlayers){
                addUser (player.getName ());
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(usersPanelBackground.getImage(),Dim.CENTER_X +170,Dim.CENTER_Y-280,480,500,null);
    }

    //getters:


    public ArrayList<Player> getSavedPlayers() {
        return savedPlayers;
    }
}
