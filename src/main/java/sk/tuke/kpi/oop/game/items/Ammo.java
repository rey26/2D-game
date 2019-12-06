package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Ripley;

public class Ammo extends AbstractActor implements Usable<Ripley> {
    public Ammo(){
        Animation animation = new Animation(
            "sprites/ammo.png");

        setAnimation(animation);
    }
    @Override
    public void useWith(@NotNull Ripley ripley) {
        if(ripley.getAmmo() < 500) {
            Scene scene = getScene();
            assert scene != null;
            scene.removeActor(this);
            if(ripley.getAmmo() < 450) {
                ripley.setAmmo(ripley.getAmmo() + 50);
            } else {
                ripley.setAmmo(500);
            }
        }
    }

    @Override
    public Class<Ripley> getUsingActorClass() {
        return Ripley.class;
    }
}
