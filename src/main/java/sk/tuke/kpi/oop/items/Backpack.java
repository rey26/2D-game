package sk.tuke.kpi.oop.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

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
    }

    public int getCapacity() {
        return this.capacity;
    }

    public List<Collectible> getContent() {
        return content;
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
        this.content.remove(this.content.indexOf(actor));
    }

    public Iterator<Collectible> iterator(){
//        for(Collectible item : content){
//
//        }
        return null;
    }

    @Nullable
    @Override
    public Collectible peek() {
        return content.get(content.size() - 1);
    }

    @Override
    public void shift() {
        Collections.rotate(content, 1);
    }
}
