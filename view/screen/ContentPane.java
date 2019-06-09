package view.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import controller.main.Administrator;
import controller.player.Player;
import model.fileManagement.FileManager;
import controller.ship.SpaceShip;


public class ContentPane extends JPanel {

    //attributes:

    InvitationPanel invitationPanel ;
    UsersPanel usersPanel ;
    MenuPanel menuPanel ;
    GamePanel gamePanel ;
    EscapePanel escapePanel ;
    GameOverPanel gameOverPanel ;
    WinPanel winPanel;

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

    public void saveState() {
//		fileManager.save(gamePanel.ship1);
    }

//	public void loadState() {
//		FileManager.load();
//	}


    public void afterInvitationPanel(){
        invitationPanel.setVisible(false);
        usersPanel = new UsersPanel(this);
        add(usersPanel);
    }

    public void afterUsersPanel(){
        usersPanel.setVisible(false);
        menuPanel = new MenuPanel(this);
        add(menuPanel);
    }

    public void afterMenuPanel(){
        if(menuPanel.newGameSelected) {
            menuPanel.setVisible(false);
            add(escapePanel);
            prepareGamePanel();
            add(gamePanel);
            gamePanel.requestFocus();
        }
        if(menuPanel.resumeGameSelected) {
            menuPanel.setVisible(false);
            add(escapePanel);
//			ContentPane.this.add(gamePanel = new GamePanel(FileManager.load(),menuPanel.player));
            gamePanel.requestFocus();
        }
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
        player = new Player(playerName) ;
        prepareAdministrator() ;
    }


}
