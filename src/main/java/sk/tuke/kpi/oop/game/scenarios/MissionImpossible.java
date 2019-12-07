package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorFactory;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class MissionImpossible implements SceneListener {
    private Ripley ripley;
    public static class Factory implements ActorFactory{
       @Nullable
        public Actor create(@Nullable String type,@Nullable String name) {
           if(type == null)
               return null;
           if(type.equals("ellen")) {
                return new Ripley();
           } else if (type.equals("energy")) {
                return new Energy();
           } else if (type.equals("door")) {
                return new LockedDoor();
           } else if (type.equals("access card")) {
               return new AccessCard();
           } else if (type.equals("locker")) {
               return new Locker();
           }
           return null;
        }
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ripley = new Ripley();
        scene.addActor(ripley, 50, 30);

        MovableController movableController = new MovableController(ripley);
        KeeperController keeperController = new KeeperController(ripley);
        scene.getInput().registerListener(movableController);
        scene.getInput().registerListener(keeperController);
        scene.follow(ripley);

        scene.addActor(new LockedDoor(), 60, 50);
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        ripley.showRipleyState();
    }
}
