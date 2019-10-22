package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Helicopter extends AbstractActor {
    public Helicopter() {
        super();
        setAnimation(new Animation(
            "sprites/heli.png",
            64,
            92,
            0.05f,
            Animation.PlayMode.LOOP_PINGPONG
        ));
    }
    public void searchAndDestroy(){
        getScene().getFirstActorByName("Player");
    }
    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::searchAndDestroy)).scheduleFor(this);
    }
}
