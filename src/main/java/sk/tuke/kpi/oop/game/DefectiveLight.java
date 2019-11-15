package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;

import java.util.Random;

public class DefectiveLight extends Light {

    public DefectiveLight(){
        super();
    }

    public void defectLight(){
        Random r = new Random();
        int low = 0;
        int high = 20;
        int result = r.nextInt(high-low) + low;
        if(result == 1){
            if(this.isOn()) {
                this.turnOn();
            }else {
                this.turnOff();
            }
        }
    }

    @Override
    public void addedToScene(@NotNull Scene scene){
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::defectLight)).scheduleFor(this);
    }
}
