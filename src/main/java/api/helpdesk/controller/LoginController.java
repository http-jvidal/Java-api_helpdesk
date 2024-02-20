package api.helpdesk.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.helpdesk.domain.models.User;
import api.helpdesk.dto.LoginDTO;
import api.helpdesk.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/auth")
@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> validateLogin(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        
        User userLogin = userService.findByUsername(username);
        
        if (userLogin == null) {
            return ResponseEntity.notFound().build();
        } else if (password.equals(userLogin.getPassword())) {
            userService.login(userLogin);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
