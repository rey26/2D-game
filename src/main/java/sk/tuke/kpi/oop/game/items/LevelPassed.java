package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class LevelPassed extends AbstractActor {
    public LevelPassed() {
        setAnimation(new Animation("sprites/popup_level_done.png"));
    }
}
