package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Ventilator extends AbstractActor implements Repairable {
    private Animation animation;
    public Ventilator(){
        super();
        animation = new Animation("sprites/ventilator.png", 32, 32, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(animation);
        animation.stop();
    }

    @Override
    public boolean repair() {
        animation.play();
        return false;
    }
}
