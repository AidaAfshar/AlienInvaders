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

import controller.player.Player;
import view.imaging.Background;
import view.imaging.ImageLoader;
import view.utilities.Dim;


public class UsersPanel extends JPanel {

    Background background3 = new Background("pictures/backgrounds/background3.png");
    boolean flag = false ;

    ArrayList<UserLabel> playersLabel = new ArrayList<UserLabel>(5);

    Player chosenPlayer ;

    JButton deleteButton ;
    JButton addButton ;
    JButton startButton ;
    JOptionPane optionPane ;
    JLabel label ;

    public UsersPanel() {
        super();
        initialize();
    }

    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.magenta);
        prepareBackground();
        prepareLabel();
        prepareUserLabels();
        prepareButtons();
        this.add(deleteButton);
        this.add(addButton);
        this.add(startButton);
    }

    public void prepareBackground() {
        background3.setImage(ImageLoader.Load(background3.getAddress()));
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
            public void actionPerformed(ActionEvent e) {
                addUser(JOptionPane.showInputDialog("Enter users name"));
            }

        });

        startButton = new JButton("START !");
        startButton.setBounds(200+10,Dim.MAX_Y-150,200,30);
        startButton.setFont(new Font("Footlight MT Light",Font.BOLD,20));
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                prepareChosenPlayer() ;
                flag = true ;

            }

        });

    }

    public void prepareChosenPlayer() {
        for(int i=0 ; i<playersLabel.size();i++) {
            if(playersLabel.get(i).chosen) {
                chosenPlayer = new Player(playersLabel.get(i).name);
            }
        }
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


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background3.getImage(),0,0,Dim.MAX_X,Dim.MAX_Y,null);
    }




}
