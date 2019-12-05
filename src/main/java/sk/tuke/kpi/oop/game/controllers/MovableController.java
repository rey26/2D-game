package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.map.SceneMap;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.Map;

public class MovableController implements KeyboardListener {
    private Movable movable;
    private Move<Movable> move;
    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST)
    );

    public MovableController(Movable movable) {
        this.movable = movable;
    }

    public void keyPressed(@NotNull Input.Key key) {
        Scene scene = movable.getScene();
        if(scene == null)
            return;

        SceneMap map = scene.getMap();
//        TODO
        if(map.intersectsWithWall(movable))
            return;

        if(this.move != null)
            this.move.stop();

        if (keyDirectionMap.containsKey(key)) {
           this.move = new Move<>(keyDirectionMap.get(key), Float.MAX_VALUE);
           move.scheduleFor(movable);
        }
    }

    public void keyReleased(@NotNull Input.Key key) {
        if (keyDirectionMap.containsKey(key) && this.move != null) {
            this.move.stop();
        }
    }

}
