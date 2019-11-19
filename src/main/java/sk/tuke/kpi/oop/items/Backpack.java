package sk.tuke.kpi.oop.items;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {
    private String name;
    private int capacity;
    private ArrayList<Collectible> content;

    public Backpack(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public List<Collectible> getContent() {
        List<Collectible> content = new ArrayList<>(this.content);
        return content;
    }

    public int getSize() {
        return this.content.size();
    }

    public String getName() {
        return this.name;
    }

    public void add(Collectible a){}

    @Override
    public void remove(@NotNull Collectible actor) {
        
    }
}
