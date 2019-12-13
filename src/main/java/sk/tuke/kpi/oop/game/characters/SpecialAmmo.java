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

public class SpecialAmmo extends AbstractActor {
    public SpecialAmmo() {
        setAnimation(new Animation("sprites/box_large.png"));
    }

    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new While<SpecialAmmo>(
            () -> true,
            new Invoke<>(() -> {
                List<Actor> actors = scene.getActors();
                for (Actor actor : actors) {
                    if (actor instanceof Armed && !(actor instanceof Enemy) && actor.intersects(this) && ((Armed) actor).getAmmoType() == 1) {
                        ((Armed) actor).setAmmoType(2);
                        new ActionSequence<>(
                            new Wait<>(5),
                            new Invoke<>(() -> ((Armed) actor).setAmmoType(1))
                        ).scheduleFor(this);
                    }
                }
            })
        ).scheduleFor(this);
    }
}
