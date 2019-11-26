package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.items.Ammo;
import sk.tuke.kpi.oop.items.Energy;

import java.util.List;

public class FirstSteps implements SceneListener {
    private Ripley ripley;
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ripley = new Ripley();
        scene.addActor(ripley);

        MovableController movableController = new MovableController(ripley);
        scene.getInput().registerListener(movableController);

        Energy energy = new Energy();
        scene.addActor(energy, 100, 0);

        Ammo ammo = new Ammo();
        scene.addActor(ammo, -100, 0);


    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
       List<Actor> actors = scene.getActors();
       for(Actor actor : actors) {
           if(actor instanceof Energy && actor.intersects(ripley)) {
               new Use<>((Energy)actor).scheduleFor(ripley);
           }
           if(actor instanceof Ammo && actor.intersects(ripley)) {
               new Use<>((Ammo)actor).scheduleFor(ripley);
           }
       }
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = ((windowHeight - 2 * GameApplication.STATUS_LINE_OFFSET) / 2 );
        scene.getOverlay().drawText(" | Energy: " + this.ripley.getEnergy() + " | Ammo: " + this.ripley.getAmmo(), -300, yTextPos);

    }
}
