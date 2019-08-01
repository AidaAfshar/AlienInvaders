package view.screen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import controller.controlSection.classLoadingAndReflection.MyClassLoader;
import view.imaging.Assets;
import view.imaging.Background;
import view.utilities.Dim;


public class EscapePanel extends JPanel {

    Background escapePanelBackground = new Background();
    ContentPane contentPane ;


    JButton resumeButton;
    JButton exitButton;
    JButton addButton ;


    public EscapePanel(ContentPane contentPane) {
        super();
        this.contentPane = contentPane ;
        initialize();
    }


    public void initialize() {
        this.setLayout(null);
        this.setBackground(Color.red);
        prepareBackground();
        prepareButtons() ;
    }

    public void prepareBackground() {
        escapePanelBackground.setImage(Assets.gamePanelBackgroundImage);
    }


    public void prepareButtons() {
        resumeButton = new JButton("RESUME");
        resumeButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y-250,350,80);
        resumeButton.setFont(new Font("Footlight MT Light",Font.BOLD,40));
        this.add(resumeButton);
        resumeButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                contentPane.resumeGameFromEscapePanel();
            }

        });


        exitButton = new JButton("EXIT");
        exitButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y-50,350,80);
        exitButton.setFont(new Font("Footlight MT Light",Font.BOLD,40));
        this.add(exitButton);
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            //    contentPane.fileManager.close();
                System.exit(0);
            }

        });


        addButton = new JButton("Add New Feature");
        addButton.setBounds(Dim.CENTER_X-200+20,Dim.CENTER_Y+150,350,80);
        addButton.setFont(new Font("Footlight MT Light",Font.BOLD,35));
        this.add(addButton);
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    openWindow() ;
                } catch (IOException ex) {
                    ex.printStackTrace ();
                }

            }

        });

    }

    JFileChooser fileChooser ;
    File selectedFile ;
    String filePath ;
    String fileName ;

    void openWindow() throws IOException {
        fileChooser = new JFileChooser (FileSystemView.getFileSystemView().getHomeDirectory()) ;
        fileChooser.showOpenDialog (null) ;
        selectedFile = fileChooser.getSelectedFile() ;
        filePath = selectedFile.getAbsolutePath () ;
        fileName = selectedFile.getName () ;
        System.out.println(filePath) ;
        System.out.println(fileName) ;

        MyClassLoader loader = new MyClassLoader (filePath,fileName) ;
        loader.initialize ();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(escapePanelBackground.getImage(),0,0,Dim.MAX_X,Dim.MAX_Y,null);
    }

    @Override
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

}
