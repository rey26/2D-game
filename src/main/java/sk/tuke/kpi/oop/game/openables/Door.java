package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;
import sk.tuke.kpi.gamelib.map.SceneMap;
import sk.tuke.kpi.gamelib.messages.MessageBus;
import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;


public class Door extends AbstractActor implements Openable, Usable<Actor> {

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    private Animation animation;
    private boolean isOpen;
    private Orientation orientation;
    public Door(String name, Orientation orientation){
        super(name);
        if (orientation == Orientation.HORIZONTAL){
            animation = new Animation(
                "sprites/hdoor.png",
                32,
                16,
                0.1f,
                Animation.PlayMode.ONCE
            );
        } else {
            animation = new Animation(
                "sprites/vdoor.png",
                16,
                32,
                0.1f,
                Animation.PlayMode.ONCE
            );
        }
        this.orientation = orientation;
        setAnimation(animation);
        animation.stop();
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        this.close();
    }

    @Override
    public void useWith(Actor actor) {
        if(isOpen)
            close();
        else
            open();
    }

    public void open() {
        Scene scene = getScene();
        if (scene == null) return;

        SceneMap map = scene.getMap();
        map.getTile(this.getPosX()/16, this.getPosY()/16).setType(MapTile.Type.CLEAR);
        int v = 1, h = 0;
        if(this.orientation == Orientation.HORIZONTAL) {
            h = 1;
            v = 0;
        }
        map.getTile((this.getPosX()/16) + v, (this.getPosY()/16) + h).setType(MapTile.Type.CLEAR);

        MessageBus messageBus = scene.getMessageBus();
        messageBus.publish(DOOR_OPENED, this);
        isOpen = true;
        this.getAnimation().setPlayMode(Animation.PlayMode.ONCE);
        this.getAnimation().play();
    }

    public void close() {
        Scene scene = getScene();
        if (scene == null) return;

        SceneMap map = scene.getMap();
        map.getTile(this.getPosX()/16, this.getPosY()/16).setType(MapTile.Type.WALL);
        int v = 1, h = 0;
        if(this.orientation == Orientation.HORIZONTAL) {
            h = 1;
            v = 0;
        }
        map.getTile((this.getPosX()/16) + v, (this.getPosY()/16) + h).setType(MapTile.Type.WALL);

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
