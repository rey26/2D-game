package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;

public class Alien extends AbstractActor implements Movable, Alive, Enemy {
    private int speed = 1;
    private Health health;

    public Alien() {
        health = new Health(100);
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1f));
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
}
