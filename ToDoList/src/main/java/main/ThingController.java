package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import response.Thing;

import java.util.List;

@RestController
public class ThingController {

    @GetMapping("/things/")
    public List<Thing> list(){
        return null;
    }

    @PostMapping("/things/")
    public int add(Thing thing){
        Storage.addThing(thing);
        return thing.getId();
    }

    @GetMapping("/things/{id}")
    public ResponseEntity get(@PathVariable int id){
        Thing thing = Storage.getThing(id);
        if (thing == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return new ResponseEntity(thing, HttpStatus.OK);
        }
    }

}
