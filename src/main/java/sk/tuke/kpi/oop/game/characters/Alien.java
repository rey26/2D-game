package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.List;

public class Alien extends AbstractActor implements Movable, Alive, Enemy {
    protected Health health;
    protected Scene scene;

    public Alien() {
        this.setHealth(100);
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1f));
        health.onExhaustion(() -> {
            this.scene.removeActor(this);
            scene.cancelActions(this);
        });
    }

    public void setHealth(int value) {
        health = new Health(value);
    }

    public Health getHealth() { return health; }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {

        this.scene = scene;

        super.addedToScene(scene);
        new While<Alien>(
            () -> true,
            new ActionSequence<>(
                new Wait<>(1),
                new Invoke<>(() -> {
                List<Actor> actors = scene.getActors();
                for (Actor actor : actors) {
                    if (actor instanceof Alive && !(actor instanceof Enemy) && actor.intersects(this)) {
                        ((Alive) actor).getHealth().drain(10);
                    }
                }
            }))

        ).scheduleFor(this);

    }

    @Override
    public void stoppedMoving() {
        new Move<>(Direction.getRandomDirection(), Float.MAX_VALUE).scheduleFor(this);
    }
}
