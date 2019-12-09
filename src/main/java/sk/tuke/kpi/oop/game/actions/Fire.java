package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.weapons.Fireable;
import sk.tuke.kpi.oop.game.weapons.Firearm;

public class Fire<A extends Armed & Actor> extends AbstractAction<A> {
    private boolean isDone;

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void execute(float deltaTime) {
        A actor = this.getActor();
        if(actor == null) {
            isDone = true;
            return;
        }

        Firearm firearm = actor.getFirearm();
        if(firearm == null){
            isDone = true;
            return;
        }

        Fireable bullet = firearm.fire();
        Scene scene = actor.getScene();
        if(scene == null) return;
        scene.addActor(bullet, actor.getPosX() + 8, actor.getPosY() + 26);

        new Move<>(Direction.fromAngle(actor.getAnimation().getRotation()), 3).scheduleFor(bullet);
        isDone = true;
    }

    @Override
    public void reset() {
        isDone = false;
    }
}
