package view.screen;

import controller.ship.SpaceShip;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ShipMouseListener implements MouseMotionListener , MouseListener{

    SpaceShip ship ;

    int x =750 ;
    int y = 790;
    int clickCount = 0 ;

    public ShipMouseListener(SpaceShip spaceShip) {
        this.ship = spaceShip ;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        x = e.getX();
        y = e.getY();
        ship.setX(x);
        ship.setY(y);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(clickCount>0) {
            x = e.getX();
            y = e.getY();
            ship.setX(x);
            ship.setY(y);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clickCount =1 ;
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
        // TODO Auto-generated method stub

    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

}
