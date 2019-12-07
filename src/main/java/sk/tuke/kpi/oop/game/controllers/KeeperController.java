package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.items.Backpack;

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
//            case U:
//                new Use<>().scheduleForIntersectingWith((Usable<?>)this.keeper);
//                break;
            case B:
                Backpack backpack = this.keeper.getBackpack();
                if (backpack == null) return;
                if (backpack.getSize() == 0) return;
//                Collectible collectible = backpack.peek();
//                if (collectible.getClass() != Usable<>) return;
//                new Use<>(collectible).scheduleFor(this.keeper);
                break;
            default:
                break;

        }
    }
}
