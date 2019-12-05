package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorFactory;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.items.Energy;

public class MissionImpossible implements SceneListener {
    public static class Factory implements ActorFactory{
       @Nullable
        public Actor create(@Nullable String type,@Nullable String name) {
           if(type == null)
               return null;
           if(type.equals("ellen")) {
                return new Ripley();
            } else if (type.equals("energy")) {
                return new Energy();
            }
            return null;
        }
    }
}
