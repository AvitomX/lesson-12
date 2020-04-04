package main;

import main.model.Thing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private static int currentId = 0;
    private static HashMap<Integer, Thing> things = new HashMap<>();

    public static int addThing(Thing thing){
        int id = currentId++;
        thing.setId(id);
        things.put(id, thing);
        return id;
    }

    public static List<Thing> getAllThings(){
        ArrayList<Thing> thingArrayList = new ArrayList<>();
        thingArrayList.addAll(things.values());
        return thingArrayList;
    }

    public static Thing getThing(int thingId){
        if (things.containsKey(thingId))
            return things.get(thingId);
        else
            return null;
    }
}
