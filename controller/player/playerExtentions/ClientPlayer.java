package controller.player.playerExtentions;

import controller.player.PlayerType;
import controller.ship.SpaceShip;

public class ClientPlayer extends Player {


    public ClientPlayer(String name, SpaceShip ship) {
        super(name, ship);
        type = PlayerType.CLIENTPLAYER ;
    }




}
