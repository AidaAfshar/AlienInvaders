package view.screen;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class UserLabelListener implements MouseMotionListener, MouseListener {

    UserLabel label ;
    boolean clicked = false ;

    public UserLabelListener(UserLabel label) {
        this.label = label ;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        label.setForeground(label.chosenColor);
        label.chosen=true ;
        label.setSize(label.width+20,label.height+20);
        clicked = true ;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        label.setForeground(label.focusColor);
        label.setSize(label.width+10,label.height+10);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(clicked==false) {
            label.setForeground(label.color);
            label.setSize(label.width,label.height);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
