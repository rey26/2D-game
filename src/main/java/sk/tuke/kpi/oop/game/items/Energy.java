package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Alive;
import sk.tuke.kpi.oop.game.characters.Health;

public class Energy extends AbstractActor implements Usable<Alive> {
    public Energy(){
        Animation animation = new Animation("sprites/energy.png");
        setAnimation(animation);
    }
    @Override
    public void useWith(@NotNull Alive alive) {
        if (alive == null) return;
        Health health = alive.getHealth();
        if(health == null) return;
        if(health.getValue() < 100) {
            Scene scene = getScene();
            assert scene != null;
            scene.removeActor(this);
            health.restore();
        }
    }

    @Override
    public Class<Alive> getUsingActorClass() {
        return Alive.class;
    }
}
