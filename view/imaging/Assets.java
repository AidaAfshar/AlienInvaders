package view.imaging;

import java.awt.image.BufferedImage;

public class Assets {

    private static Assets ourInstance = new Assets();

    public static Assets getInstance() {
        return ourInstance;
    }

    private Assets() {
    }

    //----------------------------------------------------------------------

    public static String spaceShipAddress = "pictures/spaceships/ship1.png" ;
    public static BufferedImage spaceShipImage = ImageLoader.load(spaceShipAddress);

    //beams:

    public static String bombAddress = "pictures/beams/bomb/bomb.png" ;
    public static BufferedImage bombImage = ImageLoader.load(bombAddress);

    public static String fireGlobeAddress = "pictures/beams/fire.png" ;
    public static BufferedImage fireGlobeImage = ImageLoader.load(fireGlobeAddress);

    public static String nitroGlobeAddress = "pictures/beams/nitro.png" ;
    public static BufferedImage nitroGlobeImage = ImageLoader.load(nitroGlobeAddress);

    public static String flameBallAddress = "pictures/beams/flameball.png" ;
    public static BufferedImage flameBallImage = ImageLoader.load(flameBallAddress);

    public static String multi1Address = "pictures/beams/multiBeam/1.png" ;
    public static BufferedImage multi1Image = ImageLoader.load(multi1Address);

    public static String multi2Address = "pictures/beams/multiBeam/2.png" ;
    public static BufferedImage multi2Image = ImageLoader.load(multi2Address);

    public static String multi3Address = "pictures/beams/multiBeam/3.png" ;
    public static BufferedImage multi3Image = ImageLoader.load(multi3Address);

    public static String multi4Address = "pictures/beams/multiBeam/4.png" ;
    public static BufferedImage multi4Image = ImageLoader.load(multi4Address);

    public static String multi5Address = "pictures/beams/multiBeam/5.png" ;
    public static BufferedImage multi5Image = ImageLoader.load(multi5Address);


    //bonus:

    public static String starAddress = "pictures/bonus/star.png" ;
    public static BufferedImage starImage = ImageLoader.load(starAddress);

    public static String purpleCubeAddress = "pictures/bonus/purpleCube.png" ;
    public static BufferedImage purpleCubeImage = ImageLoader.load(purpleCubeAddress);

    public static String diamondAddress = "pictures/bonus/diamond.png" ;
    public static BufferedImage diamondImage = ImageLoader.load(diamondAddress);

    public static String redCubeAddress = "pictures/bonus/redCube.png" ;
    public static BufferedImage redCubeImage = ImageLoader.load(redCubeAddress);

    public static String coinAddress = "pictures/bonus/coin.png" ;
    public static BufferedImage coinImage = ImageLoader.load(coinAddress);

    public static String colorCircleAddress = "pictures/bonus/colorCircle.png" ;
    public static BufferedImage colorCircleImage = ImageLoader.load(colorCircleAddress);

    //alien attack:

    public static String spikeAddress = "pictures/aliens/spikes/spike.png" ;
    public static BufferedImage spikeImage = ImageLoader.load(spikeAddress);

    //aliens:

    public static String hester1Address = "pictures/aliens/hester/1.png" ;
    public static BufferedImage hester1Image = ImageLoader.load(hester1Address);

    public static String hester2Address = "pictures/aliens/hester/2.png" ;
    public static BufferedImage hester2Image = ImageLoader.load(hester2Address);

    public static String ophelia1Address = "pictures/aliens/ophelia/1.png" ;
    public static BufferedImage ophelia1Image = ImageLoader.load(ophelia1Address);

    public static String ophelia2Address = "pictures/aliens/ophelia/2.png" ;
    public static BufferedImage ophelia2Image = ImageLoader.load(ophelia2Address);

    public static String augustus1Address = "pictures/aliens/augustus/1.png"  ;
    public static BufferedImage augustus1Image = ImageLoader.load(augustus1Address);

    public static String augustus2Address = "pictures/aliens/augustus/2.png" ;
    public static BufferedImage augustus2Image = ImageLoader.load(augustus2Address);

    public static String bloodrex1Address = "pictures/aliens/bloodrex/1.png" ;
    public static BufferedImage bloodrex1Image = ImageLoader.load(bloodrex1Address);

    public static String bloodrex2Address = "pictures/aliens/bloodrex/2.png" ;
    public static BufferedImage bloodrex2Image = ImageLoader.load(bloodrex2Address);

    public static String gravitusAddress = "pictures/aliens/gravitus/gravitus.png" ;
    public static BufferedImage gravitusImage = ImageLoader.load(gravitusAddress);


    //backgrounds:

    public static String gamePanelBackgroundAddress = "pictures/backgrounds/gamePanel.png" ;
    public static BufferedImage gamePanelBackgroundImage = ImageLoader.load(gamePanelBackgroundAddress);

    public static String invitationPanelBackgroundAddress = "pictures/backgrounds/invitationPanel.png" ;
    public static BufferedImage invitationPanelBackgroundImage = ImageLoader.load(invitationPanelBackgroundAddress);

    public static String menuPanelBackgroundAddress = "pictures/backgrounds/menuPanel.png" ;
    public static BufferedImage menuPanelBackgroundImage = ImageLoader.load(menuPanelBackgroundAddress);

    public static String usersPanelBackgroundAddress = "pictures/backgrounds/usersPanel.png" ;
    public static BufferedImage usersPanelBackgroundImage = ImageLoader.load(usersPanelBackgroundAddress);

    public static String gameOverPanelBackgroundAddress = "pictures/backgrounds/gameOverPanel.png" ;
    public static BufferedImage gameOverPanelBackgroundImage = ImageLoader.load(gameOverPanelBackgroundAddress);

    public static String winPanelBackgroundAddress = "pictures/backgrounds/winPanel.png" ;
    public static BufferedImage winPanelBackgroundImage = ImageLoader.load(winPanelBackgroundAddress);

//    public static String singleMultiPanelBackgroundAddress = "pictures/backgrounds/singleMultiPanel.png" ;
//    public static BufferedImage singleMultiPanelBackgroundImage = ImageLoader.load(singleMultiPanelBackgroundAddress);

    public static String serverClientPanelBackgroundAddress = "pictures/backgrounds/serverClientPanel.png" ;
    public static BufferedImage serverClientPanelBackgroundImage = ImageLoader.load(serverClientPanelBackgroundAddress);

    public static String rankingPanelBackgroundAddress = "pictures/backgrounds/rankingPanel.png" ;
    public static BufferedImage rankingPanelPanelBackgroundImage = ImageLoader.load(rankingPanelBackgroundAddress);


}
