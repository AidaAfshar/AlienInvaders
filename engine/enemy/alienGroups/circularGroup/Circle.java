package engine.enemy.alienGroups.circularGroup;

import engine.enemy.aliens.Alien;

public class Circle {

    int r ;
    int xc , yc ;
    int count = 6 ;

    Alien alien ;

    public Circle(int r , int xc , int yc , Alien alien){
        this.r = r ;
        this.xc = xc ;
        this.yc = yc ;
        this.alien = alien ;
    }

    public void setV(){
        if(alien.getX()>xc && alien.getY()<yc){
            alien.setVx(-1);
            alien.setVy(-1);
        }else if(alien.getX()<xc && alien.getY()<yc){
            alien.setVx(-1);
            alien.setVy(+1);
        }else if(alien.getX()<xc && alien.getY()>yc){
            alien.setVx(+1);
            alien.setVy(+1);
        }else if(alien.getX()>xc && alien.getY()>yc){
            alien.setVx(+1);
            alien.setVy(-1);
        }
    }

    public void placeAliens(){
        for(int i=0 ; i<count ; i++){

        }
    }

}
