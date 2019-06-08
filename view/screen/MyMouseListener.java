package view.screen;

import controller.ship.SpaceShip;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseMotionListener , MouseListener{

    SpaceShip ship ;

    int x =750 ;
    int y = 790;
    int clickCount = 0 ;

    public MyMouseListener(SpaceShip spaceShip) {
        this.ship = spaceShip ;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        ship.setX(e.getX());
        ship.setY(e.getY());
//        x = e.getX();
//        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(clickCount>0) {

            ship.setX(e.getX());
            ship.setY(e.getY());
//            x = e.getX();
//            y = e.getY();
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
