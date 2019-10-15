package sk.tuke.kpi.oop.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends BreakableTool {
    private Animation normalAnimation;
    public Hammer(){
        super(1);
        this.normalAnimation = new Animation("sprites/hammer.png");
        setAnimation(this.normalAnimation);
    }
    public Hammer(int remainingUses){
        super(remainingUses);
        this.normalAnimation = new Animation("sprites/hammer.png");
        setAnimation(this.normalAnimation);
    }
}
