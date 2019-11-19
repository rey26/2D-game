package sk.tuke.kpi.oop.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool<A extends Actor> extends AbstractActor implements Usable<A> {
    protected int remainingUses;
    public BreakableTool(int remainingUses){
        this.remainingUses = remainingUses;
    }


    public void useWith(A a){
        this.remainingUses--;
        if(this.remainingUses == 0){
            Scene scene = getScene();
            scene.removeActor(this);
        }
    }
}
