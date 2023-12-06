package api.helpdesk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.helpdesk.domain.models.User;
import api.helpdesk.dto.Login;
import api.helpdesk.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins =  {"http://localhost:4200"})
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
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
    public User CreateUser (@RequestBody User user){
        return userService.createUser(user);
        
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id){
        Optional<User> userId = userService.findById(id);
        if(userId.isPresent()){
            userService.delete(id);
            return ResponseEntity.noContent().build();
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
    public ResponseEntity<Login> login(@RequestBody Login login){
        Optional<User> userLogin = userService.findByUsername(login.getUsername());
        Optional<User> userPass = userService.findByPassword(login.getPassword());
        Login userLogado = userService.login(login);
        if(userLogin.isPresent() && userPass.isPresent() ){
            if(login.getUsername().equals(userLogin.get().getUsername()) && login.getPassword().equals(userPass.get().getPassword())){
                return ResponseEntity.ok(userLogado);
            }
        }
        
        return new ResponseEntity<Login>(HttpStatus.NOT_FOUND);
    }

    
}
