package sk.tuke.kpi.oop.game.weapons;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Bullet extends AbstractActor implements Fireable {
    public Bullet(){
        setAnimation(new Animation("sprites/bullet", 16, 16));
    }
}
