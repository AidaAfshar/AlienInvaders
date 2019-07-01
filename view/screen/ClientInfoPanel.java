package view.screen;

import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientInfoPanel extends JPanel {

    Background clientInfoPanelBackground = new Background();
    ContentPane contentPane ;

    String IP ;
    int port ;

    JLabel IPLabel ;
    JLabel portLabel ;
    JTextField IPTextField ;
    JTextField portTextField ;
    JButton submitButton;
    JButton connectButton ;


    public ClientInfoPanel(ContentPane contentPane) {
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
        clientInfoPanelBackground.setImage(Assets.menuPanelBackgroundImage);
    }

    public void prepareLabels(){
        IPLabel = new JLabel("Insert IP :") ;
        IPLabel.setBounds(Dim.CENTER_X-500,Dim.CENTER_Y-200,200,60);
        IPLabel.setForeground(Color.white);
        IPLabel.setFont(new Font("Aparajita", Font.BOLD , 40));
        add(IPLabel);

        portLabel = new JLabel("Insert port :") ;
        portLabel.setBounds(Dim.CENTER_X-500,Dim.CENTER_Y,200,60);
        portLabel.setForeground(Color.white);
        portLabel.setFont(new Font("Aparajita", Font.BOLD , 40));
        add(portLabel);
    }

    public void prepareTextFields(){
        IPTextField = new JTextField();
        IPTextField.setBounds(Dim.CENTER_X-200 ,Dim.CENTER_Y-200,400,60 );
        IPTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IPTextField.setText("");
            }
        });
        add(IPTextField);

        portTextField = new JTextField();
        portTextField.setBounds(Dim.CENTER_X-200 ,Dim.CENTER_Y,400,60 );
        portTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                portTextField.setText("");
            }
        });
        add(portTextField);

    }

    public void prepareButtons(){
        submitButton = new JButton("SUBMIT !");
        submitButton.setBounds(Dim.CENTER_X+400,Dim.CENTER_Y-100,200,80);
        submitButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        this.add(submitButton);
        submitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                IP = IPTextField.getText();
                port = Integer.valueOf(portTextField.getText());
            }

        });



        connectButton = new JButton("connect to server!");
        connectButton.setBounds(Dim.CENTER_X-180,Dim.CENTER_Y+200,350,70);
        connectButton.setFont(new Font("Footlight MT Light",Font.BOLD,30));
        add(connectButton);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.afterClientInfoPanel();
            }
        });



    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(clientInfoPanelBackground.getImage(),0,0,Dim.MAX_X,Dim.MAX_Y,null);
    }


    //getters & setters:


    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}