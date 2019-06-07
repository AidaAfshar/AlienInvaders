package view.screen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {


    ContentPane contentPane ;

    public MyKeyListener(ContentPane contentPane) {
        this.contentPane = contentPane;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_ESCAPE) {
            contentPane.handleEscapePanel();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }



}
