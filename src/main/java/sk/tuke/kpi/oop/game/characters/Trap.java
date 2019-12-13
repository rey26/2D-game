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

public class Trap extends AbstractActor {
    private int stoppingTime;
    public Trap(int stoppingTime) {
        this.stoppingTime = stoppingTime;
        setAnimation(new Animation("sprites/trap_system.png"));
    }

    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new While<Trap>(
            () -> true,
            new Invoke<>(() -> {
                List<Actor> actors = scene.getActors();
                for (Actor actor : actors) {
                    if (actor instanceof Movable && !(actor instanceof Enemy) && actor.intersects(this) && !((Movable) actor).isSpeedModified()) {
                        int speed = ((Movable) actor).getSpeed();
                        ((Movable) actor).setSpeed(0);
                        ((Movable) actor).toggleSpeedModified();
                        new ActionSequence<>(
                            new Wait<>(stoppingTime),
                            new Invoke<>(() -> {
                                ((Movable) actor).setSpeed(speed);
                                new ActionSequence<>(
                                    new Wait<>(2),
                                    new Invoke<>(((Movable) actor)::toggleSpeedModified)
                                ).scheduleFor(this);
                            })
                        ).scheduleFor(this);
                    }
                }
            })
        ).scheduleFor(this);


    }
}
