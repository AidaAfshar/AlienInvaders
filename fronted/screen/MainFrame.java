package fronted.screen;

import javax.swing.JFrame;

import fronted.utilities.Dim;


public class MainFrame extends JFrame {

    public ContentPane contentPane ;


    public MainFrame() {
        super();
        initialize();
    }

    public void initialize() {
        this.setBounds(0,0,Dim.MAX_X,Dim.MAX_Y);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        contentPane = new ContentPane();
        this.add(contentPane);
        this.setContentPane(contentPane);
    }

}
