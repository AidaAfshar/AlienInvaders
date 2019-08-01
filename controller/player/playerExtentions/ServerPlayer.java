package controller.player.playerExtentions;

import controller.player.PlayerType;
import controller.ship.SpaceShip;

public class ServerPlayer extends Player{

    public ServerPlayer(String name, SpaceShip ship) {
        super(name, ship);
        type = PlayerType.SERVERPLAYER ;
    }



}
