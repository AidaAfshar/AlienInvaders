package controller.ship;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.Timer;

import controller.attackTools.*;
import view.imaging.Image;
import view.screen.BeamMouseListener;
import view.screen.GamePanel;
import view.utilities.Dim;


public class SpaceShip extends Image{


    int x ;

    int y ;
    int width ;
    int height ;
    int halfWidth ;
    int halfHeight ;
    int centerX ;
    int centerY ;
    public int coin = 0 ;
    public int power = 5 ;
    public int bombCount = 3 ;

    public transient SpaceShipTempController tempController ;
    public transient SpaceShipAttack shipAttack ;


    transient Timer shipTimer ;

    public BeamMouseListener bml ;


    public SpaceShip() {
        initialize();
    }

    public SpaceShip(String address) {
        super(address);
        initialize();
    }

    public void initialize() {
        tempController = this.new SpaceShipTempController();
        shipAttack = this.new SpaceShipAttack();
        prepareShipTimer();
        shipTimer.start();
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


    public void checkToBeInside() {
        if(this.x < 0) this.x = 0 ;
        if(this.y < 0) this.y = 0 ;
        if(this.x + this.width > Dim.MAX_X) this.x = Dim.MAX_X - this.width ;
        if(this.y + this.height > Dim.MAX_Y) this.y = Dim.MAX_Y - this.height ;
    }

    public void draw(Graphics g) {
        g.drawImage(this.getImage(), this.x , this.y, this.width, this.height, null);

    }


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

//	public void save(PrintStream p) {
//		Gson gson = new Gson();
//		p.println(gson.toJson(this));
//	}

//	public static SpaceShip Load(Scanner sc) {
//		Gson gson = new Gson();
//		return gson.fromJson(sc.nextLine(),SpaceShip.class);
//	}

    //Attack sub class :

    public class SpaceShipAttack{

        ArrayList<Bomb> bombs = new ArrayList<>(3);
        ArrayList<Beam> beams = new ArrayList<>();
//        ArrayList<FireGlobe> fireGlobe = new ArrayList<>();
//        ArrayList<NitroGlobe> nitroGlobe = new ArrayList<>();

        Timer beamTimer ;

        public SpaceShipAttack() {
            initialize();
        }

        public void initialize() {
            prepareBeamTimer();
        }

        public void attack() {
            singleBeamAttack();
            multiBeamsAttack();
            bombAttack();
        }

        private void singleBeamAttack() {
            if(bml.mousePressed_beam) {
                produceBeam();
                bml.mousePressed_beam = false ;
            }
        }

        public void produceBeam() {
            beams.add(new NitroGlobe(bml.x , bml.y));
            SpaceShip.this.tempController.increaseTemprature();
        }

        private void multiBeamsAttack() {
            if(bml.pressDown) {
                if(! beamTimer.isRunning())
                    beamTimer.start();
            }else{
                if(beamTimer.isRunning()) {
                    beamTimer.stop();
                }
            }

        }


        public void bombAttack() {
            if(bml.mousePressed_bomb) {
                if(SpaceShip.this.bombCount>0) {
                    produceBomb();
                    bml.mousePressed_bomb = false ;
                }else {
                    //TODO
                }
            }

        }

        public void produceBomb() {
            if(bombs.size()<3) {
                bombs.add(new Bomb(bml.x,bml.y));
                Bomb.count++ ;
                SpaceShip.this.bombCount-- ;
            }
        }



        public void prepareBeamTimer() {

            beamTimer = new Timer(200 , new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    produceBeam();
                }

            });
        }


        public void renderAttack(Graphics g) {
            renderBeamAttack(g);
            renderBombAttack(g);
        }


        public void renderBeamAttack(Graphics g) {
            if(this.beams.size() != 0) {
                for(Beam beam : this.beams) {
                    if(beam.getThrowPermission()) {
                        beam.draw(g);
                        beam.moveBeam();
                    }

                }
            }
        }

        public void renderBombAttack(Graphics g) {
            if(this.bombs.size() != 0) {
                for(Bomb bomb : this.bombs) {
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

        public ArrayList<Beam> getBeams() {
            return beams;
        }

        public ArrayList<Bomb> getBombs() {
            return bombs;
        }



    }// end of Attack sub class


// Temperature Controller sub class :

    public class SpaceShipTempController{

        public int temperature = 0;
        boolean tempInSafeRange = true ;

        java.util.Timer tempTimer ;
        java.util.Timer restTimer ;



        public void controlTemp(GamePanel gamePanel) {
            if(this.tempInSafeRange) {
                if(this.temperature>=100) {
                    this.tempInSafeRange=false ;
                    this.temperature=0 ;
                    bml.mousePressed_beam =false ;
                    bml.pressDown = false ;
                    gamePanel.removeMouseListener(gamePanel.bml);
                    gamePanel.remove(gamePanel.tempBar);
                    gamePanel.add(gamePanel.restLabel);
                    evokeRestTimer(gamePanel);
                }
            }

        }

        public void evokeRestTimer(GamePanel gamePanel) {
            restTimer = new java.util.Timer();
            restTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    gamePanel.addMouseListener(gamePanel.bml);
                    gamePanel.remove(gamePanel.restLabel);
                    gamePanel.add(gamePanel.tempBar);
                    SpaceShipTempController.this.tempInSafeRange=true ;
                }

            },4000);
        }


        public void prepareTempTimer() {
            tempTimer = new java.util.Timer();
            tempTimer.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {

                    if(bml.mousePressed_beam ==false && bml.pressDown ==false) {
                        SpaceShipTempController.this.decreaseTemprature();
                        if(SpaceShipTempController.this.temperature < 0) {
                            SpaceShipTempController.this.temperature = 0 ;
                        }
                    }
                }

            },0,1000);
        }


        public void decreaseTemprature() {
            this.temperature-=40 ;
        }

        public void increaseTemprature() {
            this.temperature+=5 ;
        }

    }// end of SpaceShipTempController sub class



}
