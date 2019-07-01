package view.screen;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import controller.main.Administrator;
import controller.main.client.Client;
import controller.main.server.Server;
import controller.player.Player;
import controller.ship.SpaceShip;
import model.fileManagement.FileManager;


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

    Player serverPlayer ;
    Player clientPlayer ;
    Player player ;
    Administrator admin ;

    FileManager fileManager ;


    public ContentPane() {
        super();
        initialize();
    }

    public void initialize() {
        admin = new Administrator(this);
        fileManager = new FileManager();
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
            prepareGamePanel();
            add(gamePanel);
            gamePanel.requestFocus();
        }
        if(menuPanel.isResumeGameSelected()) {
            menuPanel.setVisible(false);
            add(escapePanel);
//			ContentPane.this.add(gamePanel = new GamePanel(FileManager.load(),menuPanel.player));
            gamePanel.requestFocus();
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
        Client client = new Client(clientInfoPanel.getIP(),clientInfoPanel.getPort(),getClientPlayer()) ;
        client.start();

    }

    public void afterServerInfoPanel(){
        serverInfoPanel.setVisible(false);
        serverPanel = new ServerPanel(this);
        add(serverPanel);
        setServerPlayer(player);
        Server server = new Server(serverPanel , serverInfoPanel.getPort() , serverInfoPanel.getLevelsCount() , serverInfoPanel.getPlayersCount()) ;
        server.start();
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
        admin = new Administrator(this);
        prepareAdministrator();
        gameOverPanel.setVisible(false);
        prepareGamePanel();
        add(gamePanel);
        gamePanel.requestFocus();
    }

    public void afterWin(){
        player.initialize();
        admin = new Administrator(this);
        prepareAdministrator();
        winPanel.setVisible(false);
        prepareGamePanel();
        add(gamePanel);
        gamePanel.requestFocus();
    }

    public void prepareGamePanel() {
        gamePanel = new GamePanel(this ,admin);
        gamePanel.setVisible(true);
    }

    public void prepareAdministrator(){
        admin.setPlayer(player);
    }

    //getters & setters:


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(String playerName) {
        player = new Player(playerName , new SpaceShip()) ;
        prepareAdministrator() ;
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
