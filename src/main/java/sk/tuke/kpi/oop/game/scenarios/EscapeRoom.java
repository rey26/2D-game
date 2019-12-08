package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorFactory;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

public class EscapeRoom implements SceneListener {
    private Ripley ripley;
    public static class Factory implements ActorFactory{
       @Nullable
        public Actor create(@Nullable String type,@Nullable String name) {
           if(type == null)
               return null;
           if(type.equals("ripley")) {
                return new Ripley();
           } else if (type.equals("energy")) {
                return new Energy();
           } else if (type.equals("ammo")) {
                return new Ammo();
           } else if (type.equals("alien")) {
               return new Alien();
           } else if (type.equals("alien mother")) {
               return new AlienMother();
           }
           return null;
        }
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
//        ripley = new Ripley();
//        scene.addActor(ripley, 40, 30);

        ripley = scene.getFirstActorByType(Ripley.class);

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
