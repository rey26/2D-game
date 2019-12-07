package sk.tuke.kpi.oop.game.openables;

public class LockedDoor extends Door {
    private boolean isLocked;
    public LockedDoor() {
        super();
        this.isLocked = true;
    }

    public void lock() {
        this.close();
    }

    public void unlock() {
        this.open();
    }

    public boolean isLocked() {
        return this.isLocked;
    }
}
