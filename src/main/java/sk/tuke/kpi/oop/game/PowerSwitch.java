package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.graphics.Color;

public class PowerSwitch<T extends Switchable> extends AbstractActor {
    private T device;
    public PowerSwitch(T t){
        this.device = t;
        setAnimation(new Animation("sprites/switch.png"));
    }

    public T getDevice(){
        return this.device;
    }

    public void switchOn(){
        this.device.turnOn();
        getAnimation().setTint(Color.GRAY);
    }

    public void switchOff(){
        this.device.turnOff();
        getAnimation().setTint(Color.GRAY);
    }

}
