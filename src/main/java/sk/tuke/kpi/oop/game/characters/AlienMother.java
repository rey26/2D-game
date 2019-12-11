package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class AlienMother extends Alien {
    public AlienMother() {
        super();
        Health health = new Health(20);
        setAnimation(new Animation("sprites/mother.png", 112, 162, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
        Scene scene = getScene();
        if(scene == null) return;
        health.onExhaustion(() -> {
            scene.removeActor(this);
            scene.cancelActions(this);
        });
    }
}
