package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.items.Collectible;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.List;

public class KeeperController implements KeyboardListener {
    private Keeper keeper;

    public KeeperController(Keeper keeper) {
        this.keeper = keeper;
    }

    public void keyPressed(@NotNull Input.Key key) {
        switch (key){
            case ENTER:
                new Take<>().scheduleFor(this.keeper);
                break;
            case S:
                new Shift<>().scheduleFor(this.keeper);
                break;
            case BACKSPACE:
                new Drop<>().scheduleFor(this.keeper);
                break;
            case U:
                Scene scene = keeper.getScene();
                if(scene == null) return;
                List<Actor> actors = scene.getActors();
                for(Actor actor : actors) {
                    if(actor instanceof Usable && actor.intersects(keeper)) {
                        new Use<>((Usable<?>)actor).scheduleForIntersectingWith(keeper);
                    }
                }
                break;
            case B:
                Backpack backpack = this.keeper.getBackpack();
                if (backpack == null) return;
                if (backpack.getSize() == 0) return;
                Collectible collectible = backpack.peek();
                if (collectible instanceof Usable) {
                    new Use<>((Usable<?>)collectible).scheduleForIntersectingWith(this.keeper);
                }
                break;
            default:
                break;

        }
    }
}
