package fit.cvut.EventPro.controller;

import fit.cvut.EventPro.entity.UserEntity;
import fit.cvut.EventPro.exception.UserAlreadyExistException;
import fit.cvut.EventPro.exception.UserNotFoundException;
import fit.cvut.EventPro.model.User;
import fit.cvut.EventPro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserEntity user) {
        try {
            User userEntity = userService.verify(user);
            return ResponseEntity.ok(userEntity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Username or password is wrong!");
        }
    }

    @PostMapping("/add")
    public ResponseEntity registration(@RequestBody UserEntity user) {
        try {
            return ResponseEntity.ok(userService.registration(user));
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/byId")
    public ResponseEntity getOneUser(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(userService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to get");
        }
    }
}
