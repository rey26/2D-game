package sk.tuke.kpi.oop.tools;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

public abstract class BreakableTool extends AbstractActor {
    protected int remainingUses;
    public BreakableTool(int remainingUses){
        this.remainingUses = remainingUses;
    }
    public void use(){
        this.remainingUses--;
        if(this.remainingUses == 0){
            Scene scene = getScene();
            scene.removeActor(this);
        }
    }
}
