package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public class Cooler extends AbstractActor implements Switchable {
    private boolean isOn;
    private Reactor reactor;
    private Animation animation;
    public Cooler(Reactor reactor){
        super();
        animation = new Animation(
            "sprites/fan.png",
            32,
            32,
            0.05f,
            Animation.PlayMode.LOOP_PINGPONG
        );
        setAnimation(animation);
        isOn = true;
        this.reactor = reactor;
    }

    public boolean isOn(){
        return isOn;
    }

    public void turnOn(){
        isOn = true;
        animation.play();
    }

    public void turnOff(){
        isOn = false;
        animation.stop();
    }

    protected void coolReactor(){
        if(isOn){
            this.reactor.decreaseTemperature(1);
        }
    }
    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
       new Loop<>(new Invoke<>(this::coolReactor)).scheduleFor(this);
    }
}
