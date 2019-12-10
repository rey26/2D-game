package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.characters.Armed;

public class Ammo extends AbstractActor implements Usable<Armed> {
    public Ammo(){
        Animation animation = new Animation("sprites/ammo.png");
        setAnimation(animation);
    }
    @Override
    public void useWith(@NotNull Armed armed) {
        Scene scene = getScene();
        if (scene == null) return;
        if (armed == null) return;
        scene.removeActor(this);
        armed.getFirearm().reload(100);
    }


    @Override
    public Class<Armed> getUsingActorClass() {
        return Armed.class;
    }
}
