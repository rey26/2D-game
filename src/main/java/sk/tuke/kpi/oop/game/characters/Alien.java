package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

public class Alien extends AbstractActor implements Movable, Alive, Enemy {
    private int speed = 1;
    private Health health;

    public Alien() {
        this.setHealth(100);
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1f));
    }

    public void setHealth(int value) {
        health = new Health(value);
    }

    public Health getHealth() {return health; }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
    }

    @Override
    public void stoppedMoving() {
        new Move<>(Direction.getRandomDirection(), Float.MAX_VALUE).scheduleFor(this);
    }
}
