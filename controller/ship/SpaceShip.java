package controller.ship;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.Timer;

import controller.attackTools.*;
import controller.player.Player;
import view.imaging.Assets;
import view.imaging.Image;
import view.imaging.ImageLoader;
import view.screen.BeamMouseListener;
import view.screen.GamePanel;
import view.utilities.Dim;


public class SpaceShip extends Image{


//    int x ,y;
//    int width,height ;
    int halfWidth , halfHeight ;
    int centerX , centerY ;

    Player player ;


    public SpaceShip(Player player) {
        super();
        this.player = player ;
        initialize();
    }


    public void initialize() {
        setImage(Assets.spaceShipImage);
    }

    //SPACESHIP ATTACK :

    ArrayList<Bomb> bombs = new ArrayList<>(3);
    ArrayList<Beam> beams = new ArrayList<>();
    BeamType beamType = BeamType.FLAMEBALL;


        public void produceBeam(int x , int y) {
            if(isTempInSafeRange()) {
                beams.add(diagnoseBeamType(getBeamType(),x,y));
                increaseTemperature();
            }
        }

    public Beam diagnoseBeamType(BeamType type ,int x , int y){
        if(type.equals(BeamType.FLAMEBALL)) {
            setBeamType(BeamType.FLAMEBALL);
            return new FlameBall(x, y);
        }
        if(type.equals(BeamType.FIREGLOBE)) {
            setBeamType(BeamType.FIREGLOBE);
            return new FireGlobe(x, y);
        }
        if(type.equals(BeamType.NITROGLOBE)) {
            setBeamType(BeamType.NITROGLOBE);
            return new NitroGlobe(x, y);
        }

        return null ;
    }




    public void produceBomb(int x , int y) {
            if(isTempInSafeRange()) {
                if (bombs.size() < 3) {
                    bombs.add(new Bomb(x, y));
                    player.setBombCount(player.getBombCount() - 1);
                }
            }
        }



        public void renderAttack(Graphics g) {
            renderBeamAttack(g);
            renderBombAttack(g);
        }


        public void renderBeamAttack(Graphics g) {
            if(beams.size() != 0) {
                for(Beam beam : beams) {
                    if(beam.getThrowPermission()) {
                        beam.draw(g);
                        beam.moveBeam();
                    }

                }
            }
        }

        public void renderBombAttack(Graphics g) {
            if(bombs.size() != 0) {
                for(Bomb bomb : bombs) {
                    if(bomb.getThrowPermission()) {
                        bomb.draw(g);
                        bomb.moveBeam();
                    }else if(bomb.isExploded()) {
//                        bomb.renderExplosion(g);
//                        bomb.j++ ;
//                        if(bomb.j>13)
//                            bomb.explode = false ;
                    }
                }
            }
        }


        //TEMPERATURE :

        int temperature = 0;
        int maximumTemp = 100 ;
        boolean tempInSafeRange = true ;

        java.util.Timer tempTimer ;

        public void prepareTempTimer() {
            tempTimer = new java.util.Timer();
            tempTimer.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                        decreaseTemperature();
                        if(temperature < 0) {
                            temperature = 0 ;
                    }
                }

            },0,1000);
        }



    public void decreaseTemperature() {
        this.temperature-=5 ;
    }

    public void increaseTemperature() {
        this.temperature+=5 ;
    }

    //---

    public void setDimensions() {
        this.halfWidth = (int) getWidth()/2 ;
        this.halfHeight = (int) getHeight()/2 ;

//        setX(Dim.CENTER_X - halfWidth - 10);
//        setY(Dim.MAX_Y - getHeight() - 10);

        setX(Dim.CENTER_X );
        setY(Dim.MAX_Y - 30);


        this.centerX = getCenterX();
        this.centerY = getCenterY();
    }


    public void checkXToBeInside(){
        if(getX() < -15)
            this.x = -15 ;
        if(getX() + getWidth() > Dim.MAX_X)
            this.x = Dim.MAX_X - getWidth();
    }

    public void checkYToBeInside(){
        if(getY() < -35)
            this.y = -35 ;
        if(getY() + getHeight() > Dim.MAX_Y)
            this.y = Dim.MAX_Y - getHeight();
    }

    public void draw(Graphics g) {
        g.drawImage(getImage(), getX() ,getY(), getWidth(),getHeight(), null);

    }



    //getters & setters :

    public int getCenterY() {
            return getX() + this.halfWidth;
        }

    public int getCenterX() {
        return getY() + this.halfHeight;
    }

    @Override
    public void setX(int x) {
        this.x = x - halfWidth;
        checkXToBeInside();
    }

    @Override
    public void setY(int y) {
        this.y = y - halfHeight ;
        checkYToBeInside();
    }

    public BeamType getBeamType() {
        return beamType;
    }

    public void setBeamType(BeamType beamType) {
        this.beamType = beamType;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean isTempInSafeRange() {
        return tempInSafeRange;
    }

    public void setTempInSafeRange(boolean tempInSafeRange) {
        this.tempInSafeRange = tempInSafeRange;
    }

    public int getMaximumTemp() {
        return maximumTemp;
    }

    public void setMaximumTemp(int maximumTemp) {
        this.maximumTemp = maximumTemp;
    }

    public ArrayList<Beam> getBeams() {
        return beams;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }



}//end of class



//	public void save(PrintStream p) {
//		Gson gson = new Gson();
//		p.println(gson.toJson(this));
//	}

//	public static SpaceShip Load(Scanner sc) {
//		Gson gson = new Gson();
//		return gson.fromJson(sc.nextLine(),SpaceShip.class);
//	}
