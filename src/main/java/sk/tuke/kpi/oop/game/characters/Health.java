package sk.tuke.kpi.oop.game.characters;

import java.util.ArrayList;
import java.util.List;

public class Health {
    private int actual, max;
    private boolean isExhausted = false;
    private List<ExhaustionEffect> exhaustionEffects;

    public Health(int initial, int max) {
        this.actual = initial;
        this.max = max;
        this.exhaustionEffects = new ArrayList<>();
    }

    public Health(int health) {
        this.actual = this.max = health;
        this.exhaustionEffects = new ArrayList<>();
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
        if(actual < 1) {
            this.exhaust();
        }
    }

    public void exhaust() {

        actual = 0;
        if(isExhausted)
            return;
        for (ExhaustionEffect exhaustionEffect : exhaustionEffects) {
            exhaustionEffect.apply();
        }
        isExhausted = true;
    }

    public int getValue() {
        return this.actual;
    }

    public void onExhaustion(ExhaustionEffect effect) {
        if(effect == null) return;
        this.exhaustionEffects.add(effect);
    }
}
