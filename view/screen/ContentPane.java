package view.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.fileManagement.FileManager;
import controller.ship.SpaceShip;


public class ContentPane extends JPanel {

    //attributes:

    InvitationPanel invitationPanel ;
    UsersPanel usersPanel ;
    MenuPanel menuPanel ;
    GamePanel gamePanel ;
    EscapePanel escapePanel ;

    FileManager fileManager ;

    Timer timer ;


    public ContentPane() {
        super();
        initialize();
    }

    public void initialize() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.blue);

        fileManager = new FileManager();

        invitationPanel= new InvitationPanel();
        this.add(invitationPanel);
        usersPanel = new UsersPanel();
        escapePanel = new EscapePanel(this);

        prepareTimer();
        timer.start();


    }


    public void saveState() {
//		fileManager.save(gamePanel.ship1);
    }

//	public void loadState() {
//		FileManager.load();
//	}

    public void prepareTimer() {
        timer = new Timer(500,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(invitationPanel.flag) {
                    ContentPane.this.invitationPanel.setVisible(false);
                    ContentPane.this.add(usersPanel);
                    invitationPanel.flag = false ;
                }

                if(usersPanel.flag) {
                    menuPanel = new MenuPanel(usersPanel.chosenPlayer);
                    ContentPane.this.usersPanel.setVisible(false);
                    ContentPane.this.add(menuPanel);
                    usersPanel.flag = false ;
                }

                if(menuPanel != null) {
                    if(menuPanel.flag) {
                        if(menuPanel.newGameSelected) {
                            ContentPane.this.menuPanel.setVisible(false);
                            ContentPane.this.add(escapePanel);
                            ContentPane.this.add(gamePanel = new GamePanel(new SpaceShip("pictures/spaceships/ship1.png"),menuPanel.player));
                            gamePanel.requestFocus();
                        }
                        if(menuPanel.resumeGameSelected) {
                            ContentPane.this.menuPanel.setVisible(false);
                            ContentPane.this.add(escapePanel);
//							ContentPane.this.add(gamePanel = new GamePanel(FileManager.load(),menuPanel.player));
                            gamePanel.requestFocus();
                        }

                        menuPanel.flag = false ;

                    }
                }

                if(gamePanel != null) {
                    if(MyKeyListener.escPressed) {
                        gamePanel.setVisible(false);
                        ContentPane.this.add(escapePanel);
                        escapePanel.setVisible(true);
                        escapePanel.requestFocus();
                        MyKeyListener.escPressed = false ;
                    }
                }


                if(gamePanel != null) {
                    if(escapePanel.resumeGameSelected) {
                        ContentPane.this.remove(escapePanel);
                        escapePanel.setVisible(false);
                        gamePanel.setVisible(true);
                        gamePanel.requestFocus();
                        escapePanel.resumeGameSelected = false ;
                    }
                }

            }

        });
    }



}
