package fit.cvut.EventPro.controller;

import fit.cvut.EventPro.dto.FilterDto;
import fit.cvut.EventPro.entity.EventEntity;
import fit.cvut.EventPro.entity.ReviewEntity;
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
            return ResponseEntity.ok(eventService.add(event));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody EventEntity event) {
        try {
            return ResponseEntity.ok(eventService.update(event));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/get")
    public ResponseEntity getAll(@RequestBody FilterDto filterDto) {
        try {
            return ResponseEntity.ok(eventService.getAll(filterDto.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Event controller failure.");
        }
    }

    @PostMapping("/addReview")
    public ResponseEntity addReview(@RequestBody ReviewEntity review) {
        try {
            return ResponseEntity.ok(eventService.addReview(review));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/getReviewsByEvent")
    public ResponseEntity getReviewsByEvent(@RequestBody FilterDto filterDto) {
        try {
            return ResponseEntity.ok(eventService.getReviewsByEvent(filterDto.getId()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
