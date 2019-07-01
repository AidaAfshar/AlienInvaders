package view.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.main.administrator.MultiPlayerAdministrator;
import controller.main.administrator.SinglePlayerAdministrator;
import controller.main.client.Client;
import controller.main.server.Server;
import controller.player.Player;
import controller.ship.SpaceShip;
import view.screen.gamePanel.MultiPlayerGamePanel;
import view.screen.gamePanel.SinglePlayerGamePanel;


public class ContentPane extends JPanel {

    //attributes:

    InvitationPanel invitationPanel ;
    UsersPanel usersPanel ;
    SingleMultiPanel singleMultiPanel ;
    ServerClientPanel serverClientPanel ;
    ClientInfoPanel clientInfoPanel;
    ServerInfoPanel serverInfoPanel;
    ClientPanel clientPanel ;
    ServerPanel serverPanel ;
    MenuPanel menuPanel ;
    SinglePlayerGamePanel singlePlayerGamePanel;
    MultiPlayerGamePanel multiPlayerGamePanel ;
    EscapePanel escapePanel ;
    GameOverPanel gameOverPanel ;
    WinPanel winPanel;


    Server server ;
    Client client ;

    Player serverPlayer ;
    Player clientPlayer ;
    Player player ;
    ArrayList<Player> players ;

    SinglePlayerAdministrator singlePlayerAdministrator;
    MultiPlayerAdministrator multiPlayerAdministrator ;


    public ContentPane() {
        super();
        initialize();
    }

    public void initialize() {
        singlePlayerAdministrator = new SinglePlayerAdministrator(this);
        multiPlayerAdministrator = new MultiPlayerAdministrator(this) ;
        setLayout(new BorderLayout());
        setBackground(Color.blue);
        preparePanels() ;
        add(invitationPanel);
    }

    public void preparePanels(){
        invitationPanel= new InvitationPanel(this);
        escapePanel = new EscapePanel(this);

    }


    public void afterInvitationPanel(){
        invitationPanel.setVisible(false);
        usersPanel = new UsersPanel(this);
        add(usersPanel);
    }

    public void afterUsersPanel(){
        usersPanel.setVisible(false);
        singleMultiPanel = new SingleMultiPanel(this);
        add(singleMultiPanel);
    }

    public void afterMenuPanel(){
        if(menuPanel.isNewGameSelected()) {
            menuPanel.setVisible(false);
            add(escapePanel);
            prepareSinglePlayerGamePanel();
            add(singlePlayerGamePanel);
            singlePlayerGamePanel.requestFocus();
        }
        if(menuPanel.isResumeGameSelected()) {
            menuPanel.setVisible(false);
            add(escapePanel);
//			ContentPane.this.add(singlePlayerGamePanel = new SinglePlayerGamePanel(FileManager.load(),menuPanel.player));
            singlePlayerGamePanel.requestFocus();
        }
    }

    public void afterSingleMultiPanel(){
        if (singleMultiPanel.isSinglePlayerSelected()) {
            singleMultiPanel.setVisible(false);
            menuPanel = new MenuPanel(this);
            add(menuPanel);
        }
        if (singleMultiPanel.isMultiPlayerSelected()) {
            singleMultiPanel.setVisible(false);
            serverClientPanel = new ServerClientPanel(this);
            add(serverClientPanel);
        }
    }

    public void afterServerClientPanel(){
        if (serverClientPanel.isServerSelected()) {
            serverClientPanel.setVisible(false);
            serverInfoPanel = new ServerInfoPanel(this);
            add(serverInfoPanel);
        }
        if (serverClientPanel.isClientSelected()) {
            serverClientPanel.setVisible(false);
            clientInfoPanel = new ClientInfoPanel(this);
            add(clientInfoPanel);
        }

    }

    public void afterClientInfoPanel(){
        clientInfoPanel.setVisible(false);
        clientPanel = new ClientPanel(this) ;
        add(clientPanel);
        setClientPlayer(player);
        client = new Client(clientInfoPanel.getIP(),clientInfoPanel.getPort(),getClientPlayer()) ;
        client.start();

    }

    public void afterServerInfoPanel(){
        serverInfoPanel.setVisible(false);
        serverPanel = new ServerPanel(this);
        add(serverPanel);
        setServerPlayer(player);
        server = new Server(serverPanel , serverInfoPanel.getPort() , serverInfoPanel.getLevelsCount() , serverInfoPanel.getPlayersCount()) ;
        server.start();
    }

    public void afterServerPanel(){
        preparePlayers();
        serverPanel.setVisible(false);
        add(escapePanel);
        prepareMultiPlayerGamePanel();
        add(multiPlayerGamePanel) ;
        multiPlayerGamePanel.requestFocus();

    }

    public void preparePlayers(){
        players = new ArrayList<>(server.getPlayers().size()+1) ;
        players.add(serverPlayer) ;
        players.addAll(server.getPlayers());

        for (Player player:players)
            player.preparePlayerThread();

    }

    public void handleEscapePanel(){
            singlePlayerGamePanel.setVisible(false);
            add(escapePanel);
            escapePanel.setVisible(true);
            escapePanel.requestFocus();
    }


    public void resumeGameFromEscapePanel(){
            this.remove(escapePanel);
            escapePanel.setVisible(false);
            singlePlayerGamePanel.setVisible(true);
            singlePlayerGamePanel.requestFocus();
    }

    public void showGameOverPanel(){
        gameOverPanel = new GameOverPanel(this);
        add(gameOverPanel);
        gameOverPanel.setVisible(true);
        singlePlayerGamePanel.setVisible(false);

    }

    public void showWinPanel(int score){
        winPanel = new WinPanel(this,score);
        add(winPanel);
        singlePlayerGamePanel.setVisible(false);
        winPanel.setVisible(true);
    }

    public void afterGameOver(){
        player.initialize();
        singlePlayerAdministrator = new SinglePlayerAdministrator(this);
        prepareSinglePlayerAdministrator();
        gameOverPanel.setVisible(false);
        prepareSinglePlayerGamePanel();
        add(singlePlayerGamePanel);
        singlePlayerGamePanel.requestFocus();
    }

    public void afterWin(){
        player.initialize();
        singlePlayerAdministrator = new SinglePlayerAdministrator(this);
        prepareSinglePlayerAdministrator();
        winPanel.setVisible(false);
        prepareSinglePlayerGamePanel();
        add(singlePlayerGamePanel);
        singlePlayerGamePanel.requestFocus();
    }

    public void prepareSinglePlayerGamePanel() {
        singlePlayerGamePanel = new SinglePlayerGamePanel(this , singlePlayerAdministrator);
        singlePlayerGamePanel.setVisible(true);
    }

    public void prepareSinglePlayerAdministrator(){
        singlePlayerAdministrator.setPlayer(player);
    }

    public void prepareMultiPlayerGamePanel() {
        multiPlayerGamePanel = new MultiPlayerGamePanel(this , multiPlayerAdministrator);
        multiPlayerGamePanel.setVisible(true);
    }


    public void prepareMultiPlayerAdministrator(){
        multiPlayerAdministrator.setCurrentPlayer(player);
        multiPlayerAdministrator.setPlayers(players);
    }


    //getters & setters:


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(String playerName) {
        player = new Player(playerName , new SpaceShip()) ;
        prepareSinglePlayerAdministrator() ;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
        prepareMultiPlayerAdministrator() ;
    }

    public Player getServerPlayer() {
        return serverPlayer;
    }

    public void setServerPlayer(Player serverPlayer) {
        this.serverPlayer = serverPlayer;
    }

    public Player getClientPlayer() {
        return clientPlayer;
    }

    public void setClientPlayer(Player clientPlayer) {
        this.clientPlayer = clientPlayer;
    }
}
