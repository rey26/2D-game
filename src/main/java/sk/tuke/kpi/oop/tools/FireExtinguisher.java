package sk.tuke.kpi.oop.tools;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class FireExtinguisher extends BreakableTool {
    private int availableUseTimes;

    public FireExtinguisher(){
        super(1);
        setAnimation(new Animation("sprites/extinguisher.png"));
    }

    public int getAvailableUseTimes(){
        return this.availableUseTimes;
    }

    public void use(){
        this.availableUseTimes -= 1;
        if(this.availableUseTimes == 0){
            Scene scene = getScene();
            scene.removeActor(this);
        }
    }
}
