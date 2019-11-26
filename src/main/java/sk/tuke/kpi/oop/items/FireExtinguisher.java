package sk.tuke.kpi.oop.items;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.Reactor;

public class FireExtinguisher extends BreakableTool<Reactor> implements Collectible {
    public FireExtinguisher(){
        super(1);
        setAnimation(new Animation("sprites/extinguisher.png"));
    }
}
