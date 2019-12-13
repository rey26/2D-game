package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.messages.MessageBus;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.characters.*;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;


public class Junkyard implements SceneListener {
    private Ripley ripley;
    public static class Factory implements ActorFactory{
       @Nullable
        public Actor create(@Nullable String type,@Nullable String name) {
            if(name != null){
                if (name.equals("ellen")) {
                    return new Ripley();
                } else if (name.equals("energy")) {
                    return new Energy();
                } else if (name.equals("ammo")) {
                    return new Ammo();
                } else if (name.equals("cables")) {
                    return new Cables(2);
                } else if (name.equals("alien")) {
//                    return new Alien();
                } else if (name.equals("alien mother")) {
                    return new AlienMother();
                } else if (name.equals("front door") || name.equals("exit door")) {
                    return new Door(name, Door.Orientation.VERTICAL);
                } else if (name.equals("back door")) {
                    return new Door(name, Door.Orientation.HORIZONTAL);
                } else if (name.equals("extra life")) {
                    return new ExtraLife();
                } else if (name.equals("electricity")) {
                    return new Electricity();
                } else if (name.equals("trap")) {
                    return new Trap(3);
                } else if (name.equals("special ammo")) {
                    return new SpecialAmmo();
                }

            }
           return null;
        }
    }

    @Override
    public void sceneCreated(@NotNull Scene scene) {
        MessageBus messageBus = scene.getMessageBus();
        messageBus.subscribe(World.ACTOR_ADDED_TOPIC, (actor) -> {
            if(actor instanceof Alien) {
                new Move<>(Direction.getRandomDirection(), Float.MAX_VALUE).scheduleFor((Alien)actor);
            }
        });
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {

        ripley = scene.getFirstActorByType(Ripley.class);

        MovableController movableController = new MovableController(ripley);
        KeeperController keeperController = new KeeperController(ripley);
        ShooterController shooterController = new ShooterController(ripley);
        scene.getInput().registerListener(movableController);
        scene.getInput().registerListener(keeperController);
        scene.getInput().registerListener(shooterController);
        scene.follow(ripley);

    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        ripley.showRipleyState();
    }
}
