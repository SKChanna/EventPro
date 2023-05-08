package fit.cvut.EventPro.controller;

import fit.cvut.EventPro.entity.ContactsEntity;
import fit.cvut.EventPro.entity.UserEntity;
import fit.cvut.EventPro.exception.UserAlreadyExistException;
import fit.cvut.EventPro.exception.UserNotFoundException;
import fit.cvut.EventPro.model.User;
import fit.cvut.EventPro.service.ContactService;
import fit.cvut.EventPro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody ContactsEntity contacts) {
        try {
            return ResponseEntity.ok(contactService.createNew(contacts));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Contact failed to add");
        }
    }

    @PostMapping("/get")
    public ResponseEntity getAll(@RequestBody Long id) {
        try {
            return ResponseEntity.ok(contactService.getAll(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to get");
        }
    }
}
