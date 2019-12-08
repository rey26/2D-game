package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.messages.MessageBus;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;

public class Door extends AbstractActor implements Openable, Usable<Actor> {
    private Animation animation;
    private boolean isOpen;
    public Door(){
        animation = new Animation(
            "sprites/vdoor.png",
            16,
            32,
            0.1f,
            Animation.PlayMode.ONCE
        );
        setAnimation(animation);
        animation.stop();
    }

    @Override
    public void useWith(Actor actor) {
        if(this.isOpen)
            close();
        else
            open();
    }

    public void open() {
        Scene scene = getScene();
        if (scene == null) return;
        MessageBus messageBus = scene.getMessageBus();
        messageBus.publish(DOOR_OPENED, this);
        this.isOpen = true;
        this.animation.setPlayMode(Animation.PlayMode.ONCE);
        this.animation.play();
    }

    public void close() {
        Scene scene = getScene();
        if (scene == null) return;
        MessageBus messageBus = scene.getMessageBus();
        messageBus.publish(DOOR_CLOSED, this);
        this.isOpen = false;
        this.animation.setPlayMode(Animation.PlayMode.ONCE_REVERSED);
        this.animation.play();
    }

    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }

    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
}
