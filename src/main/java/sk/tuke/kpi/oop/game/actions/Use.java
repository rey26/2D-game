package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.items.Usable;

public class Use<A extends Actor> extends AbstractAction<A> {
    private Usable<A> usable;
    public Use(Usable<A> usable) {
        this.usable = usable;
    }

    @Override
    public void execute(float deltaTime) {
        this.usable.useWith(this.getActor());
        setDone(true);
    }


}
