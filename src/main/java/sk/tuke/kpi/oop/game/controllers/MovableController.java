package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.actions.Move;

import java.util.Map;

public class MovableController implements KeyboardListener {
    private Movable movable;
    private Move move;
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
        this.move.stop();
        switch (key) {
            case UP:
                this.move = new Move(Direction.NORTH, this.movable.getSpeed());
                break;
            case RIGHT:
                this.move = new Move(Direction.EAST, this.movable.getSpeed());
                break;
            case DOWN:
                this.move = new Move(Direction.SOUTH, this.movable.getSpeed());
                break;
            case LEFT:
                this.move = new Move(Direction.WEST, this.movable.getSpeed());
                break;
            default:
                break;
        }
    }

}
