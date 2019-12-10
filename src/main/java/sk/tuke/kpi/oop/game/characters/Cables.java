package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cables extends AbstractActor {
    public Cables() {
        setAnimation(new Animation("sprites/cables.png"));
    }
}
