package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.MovableController;

public class FirstSteps implements SceneListener {
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        Ripley ripley = new Ripley();
        scene.addActor(ripley);

        MovableController movableController = new MovableController(ripley);
        scene.getInput().registerListener(movableController);
    }
}
