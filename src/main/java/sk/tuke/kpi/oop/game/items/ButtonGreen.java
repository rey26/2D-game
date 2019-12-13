package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Enemy;

import java.util.List;

public class ButtonGreen extends AbstractActor {
    public ButtonGreen() {
        setAnimation(new Animation("sprites/button_green.png"));
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {


        super.addedToScene(scene);
        new While<ButtonGreen>(
            () -> true,
            new ActionSequence<>(
                new Wait<>(1),
                new Invoke<>(() -> {
                    List<Actor> actors = scene.getActors();
                    for (Actor actor : actors) {
                        if (actor instanceof Alive && !(actor instanceof Enemy) && actor.intersects(this)) {
                            scene.addActor(new LevelPassed(), 96, 128);
                            new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        scene.getGame().stop();
                                    }
                                },
                                5000
                            );
                        }
                    }
                }))

        ).scheduleFor(this);

    }
}
