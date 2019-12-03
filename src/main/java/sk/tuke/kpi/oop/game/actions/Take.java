package sk.tuke.kpi.oop.game.actions;


import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.items.Collectible;


public class Take<A extends Keeper> extends AbstractAction<A> {

    @Override
    public void execute(float deltaTime) {
        A keeper = getActor();
        if(keeper == null)
            return;

        Scene scene = keeper.getScene();
        if(scene == null)
            return;

        for(Actor collectible : scene.getActors()){
            if (collectible.intersects(keeper) && collectible instanceof Collectible) {
                try {
                    keeper.getBackpack().add((Collectible)collectible);
                    scene.removeActor(collectible);
                } catch (IllegalStateException e) {
                    scene.getOverlay().drawText(e.getMessage(), 0, 0);
                }

            }
        }
        setDone(true);
    }
}
