package view.screen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BeamMouseListener implements MouseListener{

    public int x = 0 ;
    public int y = 0 ;
    public boolean pressDown = false ;
    public boolean mousePressed_beam = false ;
    public boolean mousePressed_bomb = false ;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressDown = true ;
        if(e.getButton() == MouseEvent.BUTTON1) {
            x = e.getX();
            y = e.getY();
            mousePressed_beam = true ;
        }
        if(e.getButton()== MouseEvent.BUTTON3) {
            x = e.getX();
            y = e.getY();
            mousePressed_bomb = true ;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pressDown = false ;

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
