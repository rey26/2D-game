package sk.tuke.kpi.oop.game.weapons;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Direction;

public class Bullet extends AbstractActor implements Fireable{
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
        getScene().removeActor(this);
    }
}
