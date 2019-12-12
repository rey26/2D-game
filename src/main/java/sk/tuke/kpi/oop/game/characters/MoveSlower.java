package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Actor;

public interface MoveSlower extends Actor {
    //must be greater than 0
    int getSlowingRate();
}
