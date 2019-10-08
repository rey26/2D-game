package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends AbstractActor {
    private Animation normalAnimation;
    private int availableUseTimes;
    public Hammer(){
        this.availableUseTimes = 1;
        this.normalAnimation = new Animation("sprites/hammer.png");
        setAnimation(this.normalAnimation);
    }

    public void use(){
        this.availableUseTimes -= 1;
        if(this.availableUseTimes == 0){
            Scene scene = getScene();
            scene.removeActor(this);
        }
    }
}
