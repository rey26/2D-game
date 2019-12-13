package sk.tuke.kpi.oop.game.characters;

import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.MessageBus;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.items.Backpack;
import sk.tuke.kpi.oop.game.weapons.Firearm;
import sk.tuke.kpi.oop.game.weapons.Gun;

public class Ripley extends AbstractActor implements Movable, Keeper, Alive, Armed {
    private boolean isSpeedModified = false;
    private int speed;
    private int ammoType;
    private Animation animation;
    private Backpack backpack;
    private Health health;
    private Firearm gun;

    public Ripley(){
        super("Ellen");

        animation = new Animation(
            "sprites/player.png",
            32,
            32,
            0.1f,
            Animation.PlayMode.LOOP_PINGPONG);
        speed = 2;
        setAnimation(animation);
        backpack = new Backpack("Ripley's backpack", 10);
        health = new Health(100, 300);
        health.onExhaustion(()-> {
            Scene scene = getScene();
            if(scene == null) return;
            MessageBus messageBus = scene.getMessageBus();
            messageBus.publish(RIPLEY_DIED, this);
            setAnimation(new Animation(
                "sprites/player_die.png",
                32,
                32,
                0.1f,
                Animation.PlayMode.ONCE));
            scene.cancelActions(this);
        });
        this.setFirearm(new Gun(100, 1000));
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isSpeedModified() {
        return isSpeedModified;
    }

    @Override
    public int getAmmoType() {
        return ammoType;
    }

    @Override
    public void setAmmoType(int type) {
        this.ammoType = type;
    }

    public void toggleSpeedModified() {
        this.isSpeedModified = !this.isSpeedModified;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public Health getHealth() { return health; }

    @Override
    public Firearm getFirearm() {
        return gun;
    }

    @Override
    public void setFirearm(Firearm weapon) {
        gun = weapon;
    }

    @Override
    public void startedMoving(Direction direction) {
        animation.play();
    }

    @Override
    public void stoppedMoving() {
        animation.stop();
    }

    public void showRipleyState() {
        int windowHeight = this.getScene().getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        this.getScene().getGame().getOverlay().drawText("| Health: " + this.health.getValue() + "| Ammo: " + this.getFirearm().getAmmo(), 100, yTextPos);
    }

    @Override
    public void collidedWithWall() {

    }

    public static final Topic<Ripley> RIPLEY_DIED = Topic.create("ripley died", Ripley.class);
}
