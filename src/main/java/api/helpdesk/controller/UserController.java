package api.helpdesk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.helpdesk.domain.models.User;
import api.helpdesk.services.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins =  {"http://localhost:4200"})
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "users")
    public List<User> FindAll(){
        return userService.findAll();    
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> FindById(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
            if(user.isPresent())
                return new ResponseEntity<User>(user.get(), HttpStatus.OK);
            else
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    
    @Bean
    @PostMapping
    public void CreateUser (@RequestBody User user){
        userService.createUser(user);
    
    }
}
