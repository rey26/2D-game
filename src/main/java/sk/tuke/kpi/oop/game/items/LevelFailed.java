package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class LevelFailed extends AbstractActor {
    public LevelFailed() {
            setAnimation(new Animation("sprites/popup_level_failed.png"));
    }
}
