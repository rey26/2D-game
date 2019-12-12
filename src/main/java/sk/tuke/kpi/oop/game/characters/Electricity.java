package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Electricity extends AbstractActor {
    private Animation animation;
    public Electricity() {
        this.animation = new Animation("sprites/electricity.png", 16, 48, 0.3f, Animation.PlayMode.LOOP);
        setAnimation(this.animation);
    }
}
