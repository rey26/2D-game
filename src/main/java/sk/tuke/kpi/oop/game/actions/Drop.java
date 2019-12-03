package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.items.Backpack;
import sk.tuke.kpi.oop.items.Collectible;


public class Drop<A extends Keeper> extends AbstractAction<A> {

    @Override
    public void execute(float deltaTime) {
        A keeper = getActor();
        if(keeper == null)
            return;

        Backpack backpack = keeper.getBackpack();
        if(backpack == null)
            return;

        Collectible collectible = backpack.peek();
        if(collectible == null)
            return;

        backpack.remove(collectible);
        Scene scene = keeper.getScene();
        if(scene != null)
            scene.addActor(collectible, keeper.getPosX(), keeper.getPosY());

        setDone(true);
    }
}
