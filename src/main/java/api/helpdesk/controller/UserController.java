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
@CrossOrigin
public class UserController {

    @Autowired
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/find/username/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        User user = userService.findByUsername(username);
        if(user != null){
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/")
    public List<User> FindAll(){
        return userService.findAll();    
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if(user.isPresent())
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        else
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
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
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        Optional<User> userId = userService.findById(id);
        User res = userService.update(user);
        if(userId.isPresent())
            return ResponseEntity.ok(res);
        else
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/roles/{username}")
    public ResponseEntity<String> getRoles(@PathVariable String username){
        try{
            User user = userService.findByUsername(username);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
            }
            
            var userRoles = userService.getUserRoles(user);
            return ResponseEntity.ok("Papéis de " + user.getUsername() + " são " + userRoles);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar papéis do usuário: " + e.getMessage());
        }
    }
    
}