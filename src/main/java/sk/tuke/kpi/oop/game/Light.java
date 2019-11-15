package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable {
    private boolean isSupplied;
    private boolean isTurnedOn;
    private Animation offAnimation;
    private Animation onAnimation;
    public Light(){
        this.isTurnedOn = true;
        this.isSupplied = true;
        this.offAnimation = new Animation("sprites/light_off.png");
        this.onAnimation = new Animation("sprites/light_on.png");
        setAnimation(onAnimation);
    }

    public void turnOn(){
        if(this.isSupplied){
            setAnimation(this.onAnimation);
        }
    }
    public void turnOff(){
        setAnimation(this.offAnimation);
    }

    public boolean isOn(){
        return this.isTurnedOn;
    }

    public void setElectricityFlow(boolean isSupplied){
        this.isSupplied = isSupplied;
        if(this.isTurnedOn && this.isSupplied){
            setAnimation(this.onAnimation);
        } else {
            setAnimation(this.offAnimation);
        }
    }
}
