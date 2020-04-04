package main;

import main.model.ThingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Thing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class ThingController {
    @Autowired
    private ThingRepository thingRepository;

    @GetMapping("/things/")
    public List<Thing> list(){
        Iterable<Thing> thingIterable = thingRepository.findAll();
        ArrayList<Thing> things = new ArrayList<>();
        thingIterable.forEach(e->things.add(e));
        return things;
    }

    @PostMapping("/things/")
    public int add(Thing thing){
        Thing newThing = thingRepository.save(thing);
        return newThing.getId();
    }

    @PutMapping("/things/{id}")
    public ResponseEntity update(@PathVariable int id,
                                 Thing thing){
        Thing updateThing = thingRepository.findById(id).get();
        updateThing.setName(thing.getName());
        updateThing.setDate(thing.getDate());
        thingRepository.save(updateThing);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PatchMapping("/things/{id}")
    public ResponseEntity updateName(@PathVariable int id,
                                 String name){
        Thing updateThing = thingRepository.findById(id).get();
        updateThing.setName(name);
        thingRepository.save(updateThing);
        return new ResponseEntity(updateThing, HttpStatus.OK);
    }

    @GetMapping("/things/{id}")
    public ResponseEntity get(@PathVariable int id){
        Optional<Thing> optionalThing = thingRepository.findById(id);
        if (!optionalThing.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return new ResponseEntity(optionalThing.get(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/things/{id}")
    public ResponseEntity delete(@PathVariable int id){
        thingRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}
