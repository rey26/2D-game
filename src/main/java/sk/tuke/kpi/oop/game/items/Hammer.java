package sk.tuke.kpi.oop.game.items;


import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class Hammer extends BreakableTool<Reactor> implements Collectible{
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
