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

public class ExtraLife extends AbstractActor implements Actor {
    public ExtraLife() {
        setAnimation(new Animation("sprites/life.png"));
    }

    public int getSpeedIncrement() {
        return 2;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new While<ExtraLife>(
            () -> true,
            new Invoke<>(() -> {
                List<Actor> actors = scene.getActors();
                for (Actor actor : actors) {
                    if (actor instanceof Movable && !(actor instanceof Enemy) && actor.intersects(this) && !((Movable) actor).isSpeedModified()) {
                       int speed = ((Movable) actor).getSpeed();
                        ((Movable) actor).setSpeed( speed*getSpeedIncrement());
                        ((Movable) actor).toggleSpeedModified();
                        new ActionSequence<>(
                            new Wait<>(5),
                            new Invoke<>(() -> {
                                ((Movable) actor).setSpeed(speed);
                                ((Movable) actor).toggleSpeedModified();
                            })
                        ).scheduleFor(this);
                    }
                }
            })
        ).scheduleFor(this);
    }
}
