package view.screen;

import controller.ship.SpaceShip;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BeamMouseListener implements MouseListener, MouseMotionListener {

    int x = 0 ;
    int y = 0 ;

    SpaceShip ship ;

    public BeamMouseListener(SpaceShip ship) {
        this.ship = ship ;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        x = e.getX();
        y = e.getY();

        if(e.getButton() == MouseEvent.BUTTON1)
            ship.produceBeam(x,y);

        if(e.getButton() == MouseEvent.BUTTON3)
            ship.produceBomb(x,y);


    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    int i=0 ;

    @Override
    public void mouseDragged(MouseEvent e) {
        x=e.getX();
        y=e.getY();
        if(i%20==0)
            ship.produceBeam(x,y);
        i++ ;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    //getters & settters :


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


}
