package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

public class LockedDoor extends Door {
    private boolean isLocked;
    public LockedDoor() {
        super();
        isLocked = true;
    }

    @Override
    public void useWith(Actor actor) {
        if (!isLocked) {
            super.useWith(actor);
        }
    }

    public void lock() {
        this.isLocked = true;
        this.close();
    }

    public void unlock() {
        this.isLocked = false;
        this.open();
    }

    public boolean isLocked() {
        return isLocked;
    }
}
