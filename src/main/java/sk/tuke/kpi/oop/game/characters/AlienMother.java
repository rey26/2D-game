package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.graphics.Animation;

public class AlienMother extends Alien {
    protected Health health;
    public AlienMother() {
        super();
        health = new Health(200);
        setAnimation(new Animation("sprites/mother.png", 112, 162, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
    }
}
