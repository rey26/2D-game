package sk.tuke.kpi.oop.game.characters;

public class Health {
    private int actual, initial, max;
    public Health(int initial, int max) {
        this.initial = this.actual = initial;
        this.max = max;
    }

    public Health(int health) {
        this.actual = this.initial = this.max = health;
    }

    public void refill(int amount) {
        if(amount < 1) return;
        actual += amount;
        if(actual > max) {
            actual = max;
        }
    }

    public void restore() {
        actual = max;
    }

    public void drain(int amount) {
        if(amount < 1) return;
        actual -= amount;
        if(actual < 0) {
            actual = 0;
        }
    }

    public void exhaust() {
        actual = 0;
    }

    public int getValue() {
        return this.actual;
    }
}
