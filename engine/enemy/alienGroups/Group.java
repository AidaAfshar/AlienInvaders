package engine.enemy.alienGroups;

import java.awt.Graphics;
import java.util.ArrayList;

import engine.enemy.aliens.Alien;

public interface Group {

    Alien alien = new Alien();
    ArrayList<Alien> aliens = new ArrayList<Alien>();


    public void moveGroup() ;
    public void renderGroup(Graphics g) ;
    public void placeAliens() ;
    public default boolean groupIsDead() {

        for(Alien alien : aliens) {
            if(alien.isAlive())
                return false ;
        }

        return true ;
    }
    public  void killTheGroup() ;





}
