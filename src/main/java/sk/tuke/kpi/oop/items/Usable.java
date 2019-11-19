package sk.tuke.kpi.oop.items;

import sk.tuke.kpi.gamelib.Actor;

public interface Usable<A extends Actor> {
    void useWith(A actor);
}
