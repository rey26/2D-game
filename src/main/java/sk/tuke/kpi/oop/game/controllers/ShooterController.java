package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.actions.Fire;
import sk.tuke.kpi.oop.game.characters.Armed;

public class ShooterController implements KeyboardListener {
    private Armed armed;

    public ShooterController(Armed armed) {
        this.armed = armed;
    }

    @Override
    public void keyPressed(Input.@NotNull Key key) {
        if(key == Input.Key.SPACE) {
            new Fire<>().scheduleFor(armed);
        }
    }

    @Override
    public void keyReleased(Input.@NotNull Key key) {

    }
}
