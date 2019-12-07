package sk.tuke.kpi.oop.game.items;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class AccessCard extends AbstractActor implements Collectible, Usable<LockedDoor> {

    public AccessCard() {
        setAnimation(new Animation("sprites/key.png"));
    }
    @Override
    public void useWith(LockedDoor door) {
        Scene scene = getScene();
        if (scene == null) return;
        if (door.isLocked()){
            door.unlock();
            scene.removeActor(this);
        }
    }

    @Override
    public Class<LockedDoor> getUsingActorClass() {
        return LockedDoor.class;
    }
}
