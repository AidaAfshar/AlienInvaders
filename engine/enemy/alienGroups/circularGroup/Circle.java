package engine.enemy.alienGroups.circularGroup;

import engine.enemy.alienGroups.Group;
import engine.enemy.aliens.Alien;
import engine.enemy.aliens.AlienName;
import engine.enemy.aliens.Bloodrex;

import java.awt.*;
import java.util.ArrayList;

public class Circle extends Group {

    int r ;
    int xc , yc ;
    int count  ;



    public Circle(int r , int xc , int yc , AlienName name ){
        super(name);
        this.r = r ;
        this.xc = xc ;
        this.yc = yc ;
        count = (int) (2*Math.PI*r)/(alien.getWidth()+50) ;
        aliens = new ArrayList<>();

        initialize();
    }

//    public void setV(Alien alien){
//        if(alien.getX()>xc && alien.getY()<yc){
//            alien.setVx(-1);
//            alien.setVy(-1);
//        }else if(alien.getX()<xc && alien.getY()<yc){
//            alien.setVx(-1);
//            alien.setVy(+1);
//        }else if(alien.getX()<xc && alien.getY()>yc){
//            alien.setVx(+1);
//            alien.setVy(+1);
//        }else if(alien.getX()>xc && alien.getY()>yc){
//            alien.setVx(+1);
//            alien.setVy(-1);
//        }
//    }


    @Override
    public void prepareEntrance() {

    }

    double teta = 0 ;

    public void placeAliens(){
        for(int i=0 ; i<count ; i++){
            Alien alien  = new Bloodrex((int)(xc + ((double)r)*Math.cos(teta)),(int)(yc + ((double)r)*Math.sin(teta)));
//          alien.setX((int)(xc + r*Math.cos(teta)));
//          alien.setY((int)(yc + r*Math.sin(teta)));
            alien.setV(this.xc , this.yc);
            aliens.add(alien);
            teta += 2*Math.PI/count;
        }
    }


    double dteta = Math.toRadians(1) ;

    @Override
    public void moveGroup() {
        for(Alien alien : aliens){
            alien.setX((int)(xc + ((double)r)*Math.cos(teta+=dteta)));
            alien.setY((int)(yc + ((double)r)*Math.sin(teta+=dteta)));
            alien.setV(this.xc , this.yc);
        }
    }


    @Override
    public void renderAliens(Graphics g) {
        m = 20 ;
        super.renderAliens(g);
    }
}
