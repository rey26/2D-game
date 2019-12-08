package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm {

    public Gun(int initial, int max) {
        super(initial, max);
    }

    @Override
    protected Fireable createBullet() {
        return new Bullet();
    }
}
