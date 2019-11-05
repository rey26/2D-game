package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.oop.tools.Hammer;

public class Gameplay extends Scenario {
    public Gameplay(){
        super();
    }
    @Override
    public void setupPlay(Scene scene){
        Reactor reactor = new Reactor();
        scene.addActor(reactor, 64, 64);
        reactor.turnOn();

        Cooler cooler = new Cooler(reactor);
        scene.addActor(cooler, 64, 90);

        new ActionSequence<>(
            new Wait<>(5),
            new Invoke<>(cooler::turnOn)
        ).scheduleFor(cooler);

        Hammer hammer = new Hammer();
        scene.addActor(hammer, 80, 100);
        new When<>(
            () -> reactor.getTemperature() >= 3000,
            new Invoke<>(() -> reactor.repairWith(hammer))
        ).scheduleFor(reactor);
    }
}
