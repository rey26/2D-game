package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.oop.items.Backpack;

public interface Keeper extends Actor {
    Backpack getBackpack();
}
