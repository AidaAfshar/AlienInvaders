package view.screen;

import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ServerInfoPanel extends JPanel {

    Background serverInfoPanelBackground = new Background();
    ContentPane contentPane ;

    int port ;
    int levelsCount ;
    int playersCount ;


    JLabel portLabel ;
    JLabel levelsCountLabel ;
    JLabel playersCountLabel ;

    JTextField portTextField ;
    JTextField levelsCountTextField ;
    JTextField playersCountTextField ;

    JButton portButton ;
    JButton levelsCountButton ;
    JButton playersCountButton;
    JButton submitButton ;



    public ServerInfoPanel(ContentPane contentPane) {
        super();
        this.contentPane = contentPane ;
        initialize();
    }


    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.red);
        prepareBackground();
        prepareLabels() ;
        prepareTextFields();
        prepareButtons();
    }

    public void prepareBackground() {
        serverInfoPanelBackground.setImage(Assets.menuPanelBackgroundImage);
    }

    public void prepareLabels(){
        portLabel = new JLabel("Insert Port :") ;
        portLabel.setBounds(Dim.CENTER_X-600,Dim.CENTER_Y-300+50,400,60);
        portLabel.setForeground(Color.white);
        portLabel.setFont(new Font("Aparajita", Font.BOLD , 40));
        add(portLabel);


        levelsCountLabel = new JLabel("Insert count of levels :") ;
        levelsCountLabel.setBounds(Dim.CENTER_X-600,Dim.CENTER_Y-100+50,400,60);
        levelsCountLabel.setForeground(Color.white);
        levelsCountLabel.setFont(new Font("Aparajita", Font.BOLD , 40));
        add(levelsCountLabel);


        playersCountLabel = new JLabel("Insert count of players :") ;
        playersCountLabel.setBounds(Dim.CENTER_X-600,Dim.CENTER_Y+100+50,400,60);
        playersCountLabel.setForeground(Color.white);
        playersCountLabel.setFont(new Font("Aparajita", Font.BOLD , 40));
        add(playersCountLabel);

    }


    public void prepareTextFields(){

        portTextField = new JTextField();
        portTextField.setBounds(Dim.CENTER_X-200 ,Dim.CENTER_Y-300+50,350,40 );
        portTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portTextField.setText("");
            }
        });
        add(portTextField);

        levelsCountTextField = new JTextField();
        levelsCountTextField.setBounds(Dim.CENTER_X-200 ,Dim.CENTER_Y-100+50,350,40 );
        levelsCountTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                levelsCountTextField.setText("");
            }
        });
        add(levelsCountTextField);

        playersCountTextField = new JTextField();
        playersCountTextField.setBounds(Dim.CENTER_X-200 ,Dim.CENTER_Y+100+50,350,40 );
        playersCountTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playersCountTextField.setText("");
            }
        });
        add(playersCountTextField);

    }

    class TextFieldMouseListener implements MouseListener {

        JTextField textField ;

        TextFieldMouseListener(JTextField textField){
           this.textField = textField ;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            textField.setText("");
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public void prepareButtons(){

//        portButton = new JButton("Submit");
//        portButton.setBounds(Dim.CENTER_X+200,Dim.CENTER_Y-400+200,100,80);
//        //submitButton.setFont(new Font("Footlight MT Light",Font.BOLD,40));
//        this.add(portButton);
//        portButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                port = Integer.valueOf(portTextField.getText());
//            }
//
//        });
//
//        levelsCountButton = new JButton("Submit");
//        levelsCountButton.setBounds(Dim.CENTER_X+200,Dim.CENTER_Y-400,100,80);
//        //levelsCountButton.setFont(new Font("Footlight MT Light",Font.BOLD,40));
//        add(levelsCountButton);
//        levelsCountButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                levelsCount = Integer.valueOf(levelsCountTextField.getText());
//            }
//
//        });
//
//
//        playersCountButton = new JButton("Submit");
//        playersCountButton.setBounds(Dim.CENTER_X+200,Dim.CENTER_Y-400,100,80);
//        //playersCountButton.setFont(new Font("Footlight MT Light",Font.BOLD,40));
//        add(playersCountButton);
//        playersCountButton.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                playersCount = Integer.valueOf(playersCountTextField.getText());
//            }
//
//        });
//


        submitButton = new JButton("SUBMIT !");
        submitButton.setBounds(Dim.CENTER_X + 400,Dim.CENTER_Y-50,200,60);
        submitButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = Integer.valueOf(portTextField.getText());
                levelsCount = Integer.valueOf(levelsCountTextField.getText());
                playersCount = Integer.valueOf(playersCountTextField.getText());

                contentPane.afterServerInfoPanel();
            }
        });



    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(serverInfoPanelBackground.getImage(),0,0,Dim.MAX_X,Dim.MAX_Y,null);
    }


}
