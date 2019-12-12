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
import sk.tuke.kpi.oop.game.Movable;

import java.util.List;

public class Cables extends AbstractActor implements MoveSlower {
    private int slowingRate = 2;
    public Cables(int slowingRate) {
        if(slowingRate < 2)
            this.slowingRate = slowingRate;
        setAnimation(new Animation("sprites/cables.png"));
    }

    @Override
    public int getSlowingRate() {
        return slowingRate;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new While<Cables>(
            () -> true,
            new Invoke<>(() -> {
                List<Actor> actors = scene.getActors();
                for (Actor actor : actors) {
                    if (actor instanceof Movable && !(actor instanceof Enemy) && actor.intersects(this) && !((Movable) actor).isSlowed()) {
                       int speed = ((Movable) actor).getSpeed();
                        ((Movable) actor).setSpeed( speed/getSlowingRate());
                        ((Movable) actor).toggleSlowed();
                        new ActionSequence<>(new Wait<>(1),
                            new Invoke<>(() -> {
                                ((Movable) actor).setSpeed(speed);
                                ((Movable) actor).toggleSlowed();

                            })
                        ).scheduleFor(this);
                    }
                }
            })
        ).scheduleFor(this);
    }
}
