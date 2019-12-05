package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;

public class Shift<A extends Keeper> extends AbstractAction<A> {
    @Override
    public void execute(float deltaTime) {
        A keeper = getActor();
        if(keeper == null){
            setDone(true);
            return;
        }

        keeper.getBackpack().shift();
        setDone(true);
    }
}
