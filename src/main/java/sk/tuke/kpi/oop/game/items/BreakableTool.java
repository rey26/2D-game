package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool<A extends Actor> extends AbstractActor implements Usable<A> {
    private int remainingUses;
    public BreakableTool(int remainingUses){
        this.remainingUses = remainingUses;
    }


    public void useWith(A a){
        this.remainingUses--;
        if(this.remainingUses == 0){

            Scene scene = getScene();
            if(scene == null)
                return;
            scene.removeActor(this);
        }
    }
}
