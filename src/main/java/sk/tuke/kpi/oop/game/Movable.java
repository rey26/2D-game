package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;

public interface Movable extends Actor {

    int getSpeed();
    boolean isSpeedModified = false;
    default void setSpeed(int speed) {}
    default boolean isSpeedModified() { return isSpeedModified;}
    default void toggleSpeedModified() {}
    default void startedMoving(Direction direction) {}
    default void stoppedMoving() {}
    default void collidedWithWall() {}
}
