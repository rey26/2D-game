package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {
    private String name;
    private int capacity;
    private List<Collectible> content;

    public Backpack(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.content = new ArrayList<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    @NotNull
    public List<Collectible> getContent() {
        return new ArrayList<>(content);
    }

    public int getSize() {
        return this.content.size();
    }

    @NotNull
    public String getName() {
        return this.name;
    }

    public void add(@NotNull Collectible a){
        if(this.content.size() > capacity) {
           throw new IllegalStateException( this.name + "is full");
        }
        this.content.add(a);
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        this.content.remove(actor);
    }

    @NotNull
    public Iterator<Collectible> iterator(){
        return this.content.iterator();
    }

    @Nullable
    @Override
    public Collectible peek() {
        if(content.isEmpty())
            return null;
        return content.get(content.size() - 1);
    }

    @Override
    public void shift() {
        Collections.rotate(content, 1);
    }
}
