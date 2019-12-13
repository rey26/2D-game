package sk.tuke.kpi.oop.game.characters;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.weapons.Gun;
import sk.tuke.kpi.oop.game.weapons.SpecialGun;

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
                    if (actor instanceof Armed && !(actor instanceof Enemy) && actor.intersects(this) && ((Armed) actor).getFirearm() instanceof Gun) {
                        ((Armed) actor).setFirearm(new SpecialGun(5, 5));
                    }
                }
            })
        ).scheduleFor(this);
    }
}
