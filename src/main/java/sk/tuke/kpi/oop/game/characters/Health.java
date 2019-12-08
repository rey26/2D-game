package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {
    private int actual, max;
    private List<ExhaustionEffect> exhaustionEffects;

    public Health(int initial, int max) {
        this.actual = initial;
        this.max = max;
        this.exhaustionEffects = new ArrayList<>();
    }

    public Health(int health) {
        this.actual = this.max = health;
    }

    @FunctionalInterface
    public interface ExhaustionEffect {
        void apply();
    }

    public void refill(int amount) {
        if(amount < 1) return;
        actual += amount;
        if(actual > max) {
            this.restore();
        }
    }

    public void restore() {
        actual = max;
    }

    public void drain(int amount) {
        if(amount < 1) return;
        actual -= amount;
        if(actual < 0) {
            this.exhaust();
        }
    }

    public void exhaust() {
        if(actual == 0)
            return;
        actual = 0;

        for (ExhaustionEffect exhaustionEffect : exhaustionEffects) {
            exhaustionEffect.apply();
        }
    }

    public int getValue() {
        return this.actual;
    }

    public void onExhaustion(ExhaustionEffect effect) {
        this.exhaustionEffects.add(effect);
    }
}
