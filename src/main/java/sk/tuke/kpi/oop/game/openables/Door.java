package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Openable, Usable<Actor> {
    private Animation animation;
    private boolean isOpen;
    public Door(){
        this.animation = new Animation(
            "sprites/vdoor.png",
            16,
            16,
            0.1f,
            Animation.PlayMode.ONCE
        );
        setAnimation(this.animation);
    }

    @Override
    public void useWith(Actor actor) {

    }

    public void open() {
        this.isOpen = true;
        this.animation.setPlayMode(Animation.PlayMode.ONCE);
        this.animation.play();
    }

    public void close() {
        this.isOpen = false;
        this.animation.setPlayMode(Animation.PlayMode.ONCE_REVERSED);
        this.animation.play();
    }

    public boolean isOpen() {
        return isOpen;
    }
}
