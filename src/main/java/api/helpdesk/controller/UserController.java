package api.helpdesk.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import api.helpdesk.domain.models.User;
import api.helpdesk.domain.models.dto.UserDTO;
import api.helpdesk.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/username/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        User user = userService.findByUsername(username);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value = "/")
    public ResponseEntity<List<UserDTO>> FindAll(){
        List<UserDTO> userDTOs = userService.findAll();
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        Optional<UserDTO> userDTO = userService.findById(id);
        if(userDTO.isPresent()){
            return new ResponseEntity<UserDTO>(userDTO.get(), HttpStatus.OK);
        }
        else
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping("/")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return ResponseEntity.ok("Usuário salvo com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar usuário, " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDTO> deleteByIdUser (@PathVariable Long id){
        Optional<UserDTO> userId = userService.findById(id);
        if(userId.isPresent()){
            userService.delete(id);
            return ResponseEntity.noContent().build(); // ERRO 204 
        } else {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody User user){
        Optional<UserDTO> userId = userService.findById(id);
        UserDTO userDTO = userService.update(user);
        if(userId.isPresent())
            return ResponseEntity.ok(userDTO);
        else
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
    }


    
}