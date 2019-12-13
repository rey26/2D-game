package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.List;

public class Hole extends AbstractActor {
    public Hole() {
        setAnimation(new Animation("sprites/hole.png", 32, 32, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
    }

    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new While<Hole>(
            () -> true,
            new Invoke<>(() -> {
                List<Actor> actors = scene.getActors();
                for (Actor actor : actors) {
                    if (actor instanceof Alive && !(actor instanceof Enemy) && actor.intersects(this)) {
                        ((Alive) actor).getHealth().exhaust();
                    }
                }
            })

        ).scheduleFor(this);

    }

}
