package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;

public interface Movable extends Actor {

    int getSpeed();
    boolean isSlowed = false;
    default void setSpeed(int speed) {}
    default boolean isSlowed() { return isSlowed;}
    default void toggleSlowed() {}
    default void startedMoving(Direction direction) {}
    default void stoppedMoving() {}
    default void collidedWithWall() {}
}
