package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.oop.game.weapons.Fireable;
import sk.tuke.kpi.oop.game.weapons.Firearm;

public interface Armed extends Fireable {
    int ammoType = 1;
    default int getAmmoType() {return ammoType;}
    default void setAmmoType(int type) {}
    Firearm getFirearm();
    void setFirearm(Firearm weapon);
}
