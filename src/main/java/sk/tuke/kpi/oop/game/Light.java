package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor {
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
    public void toggle(){
        this.isTurnedOn = !this.isTurnedOn;
        if(this.isTurnedOn && isSupplied){
            setAnimation(this.onAnimation);
        } else {
            setAnimation(this.offAnimation);
        }
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
