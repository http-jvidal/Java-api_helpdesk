package api.helpdesk.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import api.helpdesk.domain.models.User;
import api.helpdesk.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins =  "http://localhost:4200")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = userService.findById(id);
            return ResponseEntity.ok(user);
    }

    @GetMapping
    public List<User> findAll(){
        var user = userService.findAll();
            return user;
    }

    @PostMapping
    public ResponseEntity<User> createUser (@RequestBody User user){
        User userCreate = userService.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("{/id}")
                    .buildAndExpand(userCreate.getId())
                    .toUri();
        return ResponseEntity.created(location).body(userCreate);
    }

    
    
}
