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
import sk.tuke.kpi.oop.game.openables.Door;

public class EscapeRoom implements SceneListener {
    private Ripley ripley;
    public static class Factory implements ActorFactory{
       @Nullable
        public Actor create(@Nullable String type,@Nullable String name) {
           if(type == null || name == null)
               return null;
           if(name.equals("ellen")) {
                return new Ripley();
           } else if (name.equals("energy")) {
                return new Energy();
           } else if (name.equals("ammo")) {
                return new Ammo();
           } else if (name.equals("alien")) {
               return new Alien();
           } else if (name.equals("alien mother")) {
               return new AlienMother();
           } else if (name.equals("front door")) {
               return new Door();
           } else if (name.equals("back door")) {
               return new AlienMother();
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
