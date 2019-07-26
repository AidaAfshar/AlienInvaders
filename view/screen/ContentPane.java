package view.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

import controller.main.administrator.Administrator;
import controller.main.administrator.MultiPlayerAdministrator;
import controller.main.administrator.SinglePlayerAdministrator;
import controller.main.Connection.client.Client;
import controller.main.Connection.server.Server;
import controller.player.Player;
import controller.player.PlayerState;
import controller.player.PlayerType;
import controller.ship.SpaceShip;
import view.screen.gamePanel.GamePanel;
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
    GamePanel gamePanel ;
    EscapePanel escapePanel ;
    GameOverPanel gameOverPanel ;
    WinPanel winPanel;


    Server server ;
    Client client ;

    Player serverPlayer ;
    Player clientPlayer ;
    Player player ;
    ArrayList<Player> players ;


    Administrator admin ;

    public ContentPane() {
        super();
        initialize();
    }

    public void initialize() {
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


    public void afterMenuPanel(){
        if(menuPanel.isNewGameSelected()) {
            menuPanel.setVisible(false);
            add(escapePanel);
            prepareSinglePlayerAdministrator();
            prepareSinglePlayerGamePanel();
            add(gamePanel);
            gamePanel.requestFocus();
        }
        if(menuPanel.isResumeGameSelected()) {
            menuPanel.setVisible(false);
            add(escapePanel);
//			ContentPane.this.add(singlePlayerGamePanel = new SinglePlayerGamePanel(FileManager.load(),menuPanel.player));
            gamePanel.requestFocus();
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

    public void afterClientInfoPanel() throws InterruptedException{
        setClientPlayer(player);
        clientPanel = new ClientPanel(this, clientPlayer);
        add(clientPanel);
        client = new Client(clientInfoPanel.getIP(),clientInfoPanel.getPort(), clientPlayer ,clientPanel) ;
        client.start();
        clientInfoPanel.setVisible(false);

    }

    public void afterClientPanel(String clientState){
        if(clientState.equals("player")) {
            clientPlayer.setType(PlayerType.PLAYER);
            prepareGamePanelForPlayerClient();
            clientPlayer.setState(PlayerState.INGAME);
        }

        if(clientState.equals("spectator")) {
            clientPlayer.setType(PlayerType.OBSERVER);
            prepareGamePanelForObserverClient();
            clientPlayer.setState(PlayerState.INGAME);
        }
    }

    public void prepareGamePanelForPlayerClient(){
        preparePlayersForPlayerClient();
        clientPanel.setVisible(false);
        add(escapePanel);
        prepareMultiPlayerAdministrator();
        prepareMultiPlayerGamePanel();
        add(gamePanel) ;
        gamePanel.requestFocus();
    }
    public void preparePlayersForPlayerClient(){
        players = new ArrayList<>(client.getOtherPlayers().size()+1) ;

        for (Player player:client.getOtherPlayers())
            player.preparePlayer();

        players.add(client.getClientPlayer()) ;
        players.addAll(client.getOtherPlayers());


//        for (Player player:players)
//            player.preparePlayerThread();
    }

    public void prepareGamePanelForObserverClient(){
        preparePlayersForObserverClient();
        clientPanel.setVisible(false);
        add(escapePanel);
        prepareMultiPlayerAdministrator();
        prepareMultiPlayerGamePanel();
        gamePanel.removeMouseListeners();
        add(gamePanel) ;
        gamePanel.requestFocus();
    }

    public void preparePlayersForObserverClient(){
        players = new ArrayList<>(client.getOtherPlayers().size()) ;

        for (Player player:client.getOtherPlayers())
            player.preparePlayer();

        players.addAll(client.getOtherPlayers());
    }



    public void afterServerInfoPanel(){
        setServerPlayer(player);
        serverInfoPanel.setVisible(false);
        serverPanel = new ServerPanel(this , serverInfoPanel.getPlayersCount());
        add(serverPanel);
        server = new Server(serverInfoPanel.getPort() , serverPlayer , serverPanel) ;
        server.start();
    }


    public void afterServerPanel(){
        preparePlayersForServerPlayer();
        serverPanel.setVisible(false);
        add(escapePanel);
        prepareMultiPlayerAdministrator();
        prepareMultiPlayerGamePanel();
        add(gamePanel) ;
        gamePanel.requestFocus();
    }


    public void preparePlayersForServerPlayer(){

        players = new ArrayList<>(server.getOtherPlayers().size()+1) ;

        for (Player player:server.getOtherPlayers())
            player.preparePlayer();

        players.add(server.getServerPlayer()) ;
        players.addAll(server.getOtherPlayers());

    }


    public void prepareSinglePlayerGamePanel() {
        gamePanel = new SinglePlayerGamePanel(this , (SinglePlayerAdministrator) admin);
        gamePanel.setVisible(true);
    }

    public void prepareSinglePlayerAdministrator(){
        admin = new SinglePlayerAdministrator(this) ;
        ((SinglePlayerAdministrator)admin).setPlayer(player) ;
    }

    public void prepareMultiPlayerGamePanel() {
        gamePanel = new MultiPlayerGamePanel(this ,(MultiPlayerAdministrator) admin);
        gamePanel.setVisible(true);
    }


    public void prepareMultiPlayerAdministrator(){
        admin = new MultiPlayerAdministrator(this) ;
        ((MultiPlayerAdministrator)admin).setCurrentPlayer(player);
        ((MultiPlayerAdministrator)admin).setPlayers(players);
    }


    public void handleEscapePanel(){
            gamePanel.setVisible(false);
            add(escapePanel);
            escapePanel.setVisible(true);
            escapePanel.requestFocus();
    }


    public void resumeGameFromEscapePanel(){
            this.remove(escapePanel);
            escapePanel.setVisible(false);
            gamePanel.setVisible(true);
            gamePanel.requestFocus();
    }

    public void showGameOverPanel(){
        gameOverPanel = new GameOverPanel(this);
        add(gameOverPanel);
        gameOverPanel.setVisible(true);
        gamePanel.setVisible(false);

    }

    public void showWinPanel(int score){
        winPanel = new WinPanel(this,score);
        add(winPanel);
        gamePanel.setVisible(false);
        winPanel.setVisible(true);
    }

    public void afterGameOver(){
        player.initialize();
        prepareSinglePlayerAdministrator();
        gameOverPanel.setVisible(false);
        prepareSinglePlayerGamePanel();
        add(gamePanel);
        gamePanel.requestFocus();
    }

    public void afterWin(){
        player.initialize();
        prepareSinglePlayerAdministrator();
        winPanel.setVisible(false);
        prepareSinglePlayerGamePanel();
        add(gamePanel);
        gamePanel.requestFocus();
    }



    //getters & setters:


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(String playerName) {
        player = new Player(playerName , new SpaceShip()) ;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
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
