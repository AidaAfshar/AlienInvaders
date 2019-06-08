package controller.ship;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.Timer;

import controller.attackTools.*;
import controller.player.Player;
import view.imaging.Image;
import view.imaging.ImageLoader;
import view.screen.BeamMouseListener;
import view.screen.GamePanel;
import view.utilities.Dim;


public class SpaceShip extends Image{

    static String spaceShipAddress = "pictures/spaceships/ship1.png" ;

    int x ,y;
    int width,height ;
    int halfWidth , halfHeight ;
    int centerX , centerY ;

    Player player ;

    transient Timer shipTimer ;


    public SpaceShip(Player player) {
        super(spaceShipAddress);
        this.player = player ;
        initialize();
    }


    public void initialize() {
        setImage(ImageLoader.Load(getAddress()));
        prepareShipTimer();
        shipTimer.start();
    }


    public void prepareShipTimer() {
        shipTimer = new Timer(3,new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                SpaceShip.this.x = GamePanel.ml.x - SpaceShip.this.halfWidth;
                SpaceShip.this.y = GamePanel.ml.y - SpaceShip.this.halfHeight;
                SpaceShip.this.checkToBeInside();


            }

        });

    }


    //SPACESHIP ATTACK :

        ArrayList<Bomb> bombs = new ArrayList<>(3);
        ArrayList<Beam> beams = new ArrayList<>();


        public void produceBeam(int x , int y) {
            if(isTempInSafeRange()) {
                beams.add(new FlameBall(x, y));
                increaseTemperature();
            }
        }


        public void produceBomb(int x , int y) {
            if(isTempInSafeRange()) {
                if (bombs.size() < 3) {
                    bombs.add(new Bomb(x, y));
                    Bomb.count++;
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
                        bomb.moveBomb();
                    }else if(bomb.explode) {
                        bomb.renderExplosion(g);
                        bomb.j++ ;
                        if(bomb.j>13)
                            bomb.explode = false ;
                    }
                }
            }
        }


        //TEMPERATURE :

        int temperature = 0;
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

    //getters & setters :

    public int getCenterY() {
            return this.x + this.halfWidth;
        }

    public int getCenterX() {
        return this.y + this.halfHeight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

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

    public ArrayList<Beam> getBeams() {
        return beams;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }


    public void setDimensions(int width ,int height) {
        this.width = width ;
        this.height = height ;

        this.halfWidth = (int) width/2 ;
        this.halfHeight = (int) height/2 ;

        this.x = Dim.CENTER_X - halfWidth ;
        this.y = Dim.MAX_Y - height;


        this.centerX = getCenterX();
        this.centerY = getCenterY();
    }

    public void setDimensions() {
        this.halfWidth = (int) width/2 ;
        this.halfHeight = (int) height/2 ;

        this.x = Dim.CENTER_X - halfWidth;
        this.y = Dim.MAX_Y - height ;


        this.centerX = getCenterX();
        this.centerY = getCenterY();
    }


    public void checkToBeInside() {
        if(x < 0)
            x = 0 ;
        if(y < 0)
            y = 0 ;
        if(x + width > Dim.MAX_X)
            x = Dim.MAX_X - width ;
        if(y + height > Dim.MAX_Y)
            y = Dim.MAX_Y - height ;
    }

    public void draw(Graphics g) {
        g.drawImage(getImage(), x , y, width, height, null);

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
