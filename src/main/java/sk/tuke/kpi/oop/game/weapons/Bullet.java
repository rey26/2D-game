package sk.tuke.kpi.oop.game.weapons;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.While;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Ripley;

import java.util.List;

public class Bullet extends AbstractActor implements Fireable{
    private Scene scene;
    public Bullet(){
        setAnimation(new Animation("sprites/bullet.png", 16, 16));
    }

    @Override
    public int getSpeed() {
        return 4;
    }

    @Override
    public void startedMoving(Direction direction) {
        this.getAnimation().setRotation(direction.getAngle());
    }

    @Override
    public void stoppedMoving() {
        scene.removeActor(this);
    }

    @Override
    public void collidedWithWall() {
        scene.removeActor(this);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.scene = scene;
        new While<Bullet>(
            () -> true,
            new ActionSequence<>(
                new Invoke<>(() -> {
                    List<Actor> actors = scene.getActors();
                    for (Actor actor : actors) {
                        if (actor instanceof Alive && actor.intersects(this) && !(actor instanceof Ripley) ){
                            ((Alive) actor).getHealth().drain(10);
                            scene.removeActor(this);
                        }
                    }
                }))

        ).scheduleFor(this);
    }
}
