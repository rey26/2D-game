package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.gamelib.map.SceneMap;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Move <A extends Movable & Actor> implements Action<A> {
    private Direction direction;
    private A actor;
    private float duration;
    private boolean isDone = false;
    private boolean isExecuted = false;
    public Move (Direction direction, float duration) {
        this.direction = direction;
        this.duration = duration;
    }

    public Move (Direction direction) {
        this.direction = direction;
        this.duration = -1;
    }

    @Nullable
    @Override
    public A getActor() {
        return actor;
    }

    public void setActor(A actor) {
        if(actor == null)
             return;
        this.actor = actor;
    }

    public boolean isDone() {
        return isDone;
    }

    public void reset() {
        isDone = false;
        isExecuted = false;
    }
    @Override
    public void execute(float deltaTime) {
        Scene scene = actor.getScene();
        if(scene == null)
            return;

        SceneMap map = scene.getMap();
        int posX = actor.getPosX();
        int posY = actor.getPosY();

        actor.setPosition(posX + (direction.getDx() * actor.getSpeed()), posY + (direction.getDy() * actor.getSpeed()));

        if(map.intersectsWithWall(actor)) {
            actor.stoppedMoving();
            isDone = true;
            actor.setPosition(posX, posY);
            actor.collidedWithWall();
            return;
        }
        if(!isExecuted)
            actor.startedMoving(direction);
        duration -= deltaTime;


        if (duration < 1e-5) {
            isDone = true;
            actor.stoppedMoving();
            return;
        }

        isExecuted = true;

        actor.getAnimation().setRotation(direction.getAngle());
    }

    public void stop() {
        isDone = true;
        actor.stoppedMoving();
    }
}
