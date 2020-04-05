package main;

import main.model.ThingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.model.Thing;

import java.util.Optional;

//@RestController
@Controller
public class ThingController {
    @Autowired
    private ThingsRepository thingRepository;

    @GetMapping("/")
    public String list(Model model){
        Iterable<Thing> things = thingRepository.findAll();
        model.addAttribute("things", things);
        return "index";
    }

    @PostMapping("addThing")
    public String add(Thing thing, Model model){
        thingRepository.save(thing);
        return "redirect:/";
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
