package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.oop.game.weapons.Fireable;
import sk.tuke.kpi.oop.game.weapons.Firearm;

public interface Armed extends Fireable {
    Firearm getFirearm();
    void setFirearm(Firearm weapon);
}
