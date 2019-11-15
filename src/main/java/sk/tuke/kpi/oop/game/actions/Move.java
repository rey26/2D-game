package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.Action;
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
        return this.actor;
    }

    public void setActor(A actor) {
        if(actor == null)
             return;
        this.actor = actor;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void reset() {
        this.isDone = false;
        this.isExecuted = false;
    }
    @Override
    public void execute(float deltaTime) {
        if(!this.isExecuted)
            this.actor.startedMoving(this.direction);

        if (this.duration == 0 || this.duration < -1) {
            this.isDone = true;
            this.actor.stoppedMoving();
            return;
        }
        this.duration -= deltaTime;

        this.isExecuted = true;

        switch(this.direction){
            case NORTH:
                actor.setPosition(actor.getPosX(), actor.getPosY() + actor.getSpeed());
            case EAST:
                actor.setPosition(actor.getPosX() + actor.getSpeed(), actor.getPosY());
            case SOUTH:
                actor.setPosition(actor.getPosX(), actor.getPosY() - actor.getSpeed());
            case WEST:
                actor.setPosition(actor.getPosX() - actor.getSpeed(), actor.getPosY());
        }

    }

    public void stop() {
        this.isDone = true;
        this.actor.stoppedMoving();
    }
}
