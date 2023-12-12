package api.helpdesk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.helpdesk.domain.models.User;
import api.helpdesk.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins =  {"http://localhost:4200"})
public class UserController {

    @Autowired
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public List<User> FindAll(){
        return userService.findAll();    
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> FindById(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
            if(user.isPresent())
                return new ResponseEntity<User>(user.get(), HttpStatus.OK);
            else
                return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping(value = "/name/{name}")
    public List<User> findByNameContainingIgnoreCase(@PathVariable String name){
        return userService.findByNameContainingIgnoreCase(name);
    }
    
    @PostMapping(value = "/")
    public ResponseEntity<User> createUser (@RequestBody User user){
        var existUser = userService.findByUsername(user.getUsername());
        
        if(existUser != null && existUser.getUsername() != null && !existUser.getUsername().isEmpty()){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
        userService.createUser(user);
        return new ResponseEntity<User>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id){
        Optional<User> userId = userService.findById(id);
        if(userId.isPresent()){
            userService.delete(id);
            return ResponseEntity.noContent().build(); // ERRO 204 
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable(value = "id") Long id, @RequestBody User user){
        Optional<User> userId = userService.findById(id);
        User res = userService.update(user);
        if(userId.isPresent())
            return ResponseEntity.ok(res);
        else
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user){
        User userLogin = userService.findByUsername(user.getUsername());
        

        if(user.getUsername().isEmpty() && user.getPassword().isEmpty())
            return new ResponseEntity<User>(HttpStatus.CONFLICT);

        if(user.getUsername().matches(userLogin.getUsername()) && user.getPassword().matches(userLogin.getPassword()))
            return ResponseEntity.ok(userService.login(user));

        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

    
    
}
