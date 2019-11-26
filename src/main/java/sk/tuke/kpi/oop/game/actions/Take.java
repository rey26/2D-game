package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.items.Collectible;

public class Take<A extends Actor> extends AbstractAction<A> {

    Collectible collectible;
    public Take(Collectible collectible) {
        this.collectible = collectible;
    }

    @Override
    public void execute(float deltaTime) {
//        Keeper keeper = this.getActor();
        setDone(true);
    }
}
