package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorFactory;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class MissionImpossible implements SceneListener {
    private Ripley ripley;
    public static class Factory implements ActorFactory{
       @Nullable
        public Actor create(@Nullable String type,@Nullable String name) {
           if(name == null)
               return null;
           if(name.equals("ellen")) {
                return new Ripley();
           } else if (name.equals("energy")) {
                return new Energy();
           } else if (name.equals("door")) {
                return new LockedDoor(name, Door.Orientation.VERTICAL);
           } else if (name.equals("access card")) {
               return new AccessCard();
           } else if (name.equals("locker")) {
               return new Locker();
           } else if (name.equals("ventilator")) {
               return new Ventilator();
           }
           return null;
        }
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ripley = scene.getFirstActorByType(Ripley.class);

        MovableController movableController = new MovableController(ripley);
        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(movableController);
        scene.getInput().registerListener(keeperController);
        scene.follow(ripley);

    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        ripley.showRipleyState();
    }
}
