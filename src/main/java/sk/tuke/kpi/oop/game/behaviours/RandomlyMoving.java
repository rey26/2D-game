package sk.tuke.kpi.oop.game.behaviours;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

public class RandomlyMoving<A extends Movable> implements Behaviour<A> {
    private A actor;
    public RandomlyMoving() {

    }
    public void execute(float deltaTime) {
        while (deltaTime > 0){
            new Move<>(Direction.getRandomDirection(), Float.MAX_VALUE).scheduleFor(actor);
            deltaTime--;
        }
    }

    public void setUp(A actor) {
        this.actor = actor;
    }
}
