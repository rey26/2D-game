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
//    private boolean keyPressed = false;
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

            if(this.move != null)
                this.move.stop();


            directions.add(keyDirectionMap.get(key));
            Direction dir = keyDirectionMap.get(key);
            int i = 0;
            for (Direction direction : directions) {
                if(i == directions.size() - 1)
                    break;
                i++;
                dir = dir.combine(direction);
            }
            this.move = new Move<>(dir, Float.MAX_VALUE);
            move.scheduleFor(movable);
        }
    }

    public void keyReleased(@NotNull Input.Key key) {
        if (keyDirectionMap.containsKey(key) && this.move != null) {
            this.move.stop();

//            Input key1 = movable.getScene().getInput();
//
//            if(directions.size() > 2) {
//                this.move = new Move<>(directions.get(directions.size() - 2), Float.MAX_VALUE);
//                move.scheduleFor(movable);
//            }
            directions.clear();
        }
    }

}
