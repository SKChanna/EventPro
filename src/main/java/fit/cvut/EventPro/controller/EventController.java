package fit.cvut.EventPro.controller;

import fit.cvut.EventPro.entity.EventEntity;
import fit.cvut.EventPro.exception.UserNotFoundException;
import fit.cvut.EventPro.repository.EventRepo;
import fit.cvut.EventPro.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepo eventRepo;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody EventEntity event) {
        try {
            eventService.add(event);
            return ResponseEntity.ok("Event has been added.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("event failed to add.");
        }
    }

    @PostMapping("/get")
    public ResponseEntity getAll(@RequestBody Long id) {
        try {
            return ResponseEntity.ok(eventService.getAll(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Event controller failure.");
        }
    }
}
