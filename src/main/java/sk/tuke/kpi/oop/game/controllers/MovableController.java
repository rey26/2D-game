package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MovableController implements KeyboardListener {
    private boolean keyPressed = false;
    private Movable movable;
    private Move<Movable> move;
    private Map<Input.Key, Direction> keyDirectionMap = Map.ofEntries(
        Map.entry(Input.Key.UP, Direction.NORTH),
        Map.entry(Input.Key.RIGHT, Direction.EAST),
        Map.entry(Input.Key.DOWN, Direction.SOUTH),
        Map.entry(Input.Key.LEFT, Direction.WEST)
    );
    private List<Direction> directions = new ArrayList<Direction>();

    public MovableController(Movable movable) {
        this.movable = movable;
    }

    public void keyPressed(@NotNull Input.Key key) {

        if (keyDirectionMap.containsKey(key)) {
            if(!keyPressed)
                directions.clear();

            if(this.move != null)
                this.move.stop();

            keyPressed = true;

            directions.add(keyDirectionMap.get(key));
            Direction dir = keyDirectionMap.get(key);
            for (Direction direction : directions) {
                dir = dir.combine(direction);
            }
            this.move = new Move<>(dir, Float.MAX_VALUE);
            move.scheduleFor(movable);
//            directions.clear();
        }
    }

    public void keyReleased(@NotNull Input.Key key) {
        if (keyDirectionMap.containsKey(key) && this.move != null) {
            this.move.stop();
            keyPressed = false;
        }
    }

}
