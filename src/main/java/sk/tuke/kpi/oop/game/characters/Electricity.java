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

import java.util.List;

public class Electricity extends AbstractActor {
    public Electricity() {
        setAnimation(new Animation("sprites/electricity.png", 16, 48, 0.1f, Animation.PlayMode.LOOP));
    }

    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new While<Electricity>(
            () -> true,
            new ActionSequence<>(
                new Wait<>(1),
                new Invoke<>(() -> {
                    List<Actor> actors = scene.getActors();
                    for (Actor actor : actors) {
                        if (actor instanceof Alive && !(actor instanceof Enemy) && actor.intersects(this)) {
                            ((Alive) actor).getHealth().drain(20);
                        }
                    }
                }))

        ).scheduleFor(this);

    }

}
