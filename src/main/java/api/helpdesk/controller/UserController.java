package api.helpdesk.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import api.helpdesk.domain.models.User;
import api.helpdesk.domain.repository.UserRepository;
import api.helpdesk.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private final UserService userService;
    private final UserRepository userRepository;
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
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
    
    @PostMapping("/")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try {
            userService.saveUser(user.getName(), user.getUsername(), user.getPassword(), user.getDepartamento());
            return ResponseEntity.ok("Usuário salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> deleteByIdUser (@PathVariable Long id){
        Optional<User> userId = userService.findById(id);
        if(userId.isPresent()){
            userService.delete(id);
            return ResponseEntity.noContent().build(); // ERRO 204 
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long id, @RequestBody User user){
        Optional<User> userId = userService.findById(id);
        User res = userService.update(user);
        if(userId.isPresent())
            return ResponseEntity.ok(res);
        else
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }

}