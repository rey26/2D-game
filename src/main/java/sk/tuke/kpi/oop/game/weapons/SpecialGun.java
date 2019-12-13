package sk.tuke.kpi.oop.game.weapons;

public class SpecialGun extends Firearm {

    public SpecialGun(int initial, int max) {
        super(initial, max);
    }

    @Override
    protected Fireable createBullet() {
        return new SpecialBullet();
    }
}
