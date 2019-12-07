package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Movable;

public class Alien extends AbstractActor implements Movable {
    private int speed = 1;

    public Alien() {
        setAnimation(new Animation("sprites/alien.png", 32, 32, 0.1f));
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}
