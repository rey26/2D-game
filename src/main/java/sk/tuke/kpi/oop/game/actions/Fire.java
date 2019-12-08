package sk.tuke.kpi.oop.game.actions;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.actions.Action;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;
import sk.tuke.kpi.oop.game.weapons.Firearm;

public class Fire<A extends Armed & Actor> implements Action<A> {
    private A actor;
    private boolean isDone;

    @Override
    public @Nullable A getActor() {
        return actor;
    }

    @Override
    public void setActor(@Nullable A actor) {
        if(actor == null)
            return;
        this.actor = actor;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void execute(float deltaTime) {
        if(actor == null) return;

        Firearm firearm = actor.getFirearm();
        if(firearm == null) return;

        Fireable bullet = firearm.fire();


    }

    @Override
    public void reset() {
        isDone = false;
    }
}
