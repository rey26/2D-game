package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;

public class Ripley extends AbstractActor implements Movable {
    private int speed;
    private Animation animation;
    public Ripley(){
        super("Ellen");

        this.animation = new Animation(
            "sprites/player.png",
            32,
            32,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG);
        speed = 2;
    }

    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void startedMoving(Direction direction) {
        this.animation.play();
    }

    @Override
    public void stoppedMoving() {
        this.animation.stop();
    }
}
