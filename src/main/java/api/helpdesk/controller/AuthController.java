package api.helpdesk.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import api.helpdesk.domain.models.User;
import api.helpdesk.domain.repository.UserRepository;
import api.helpdesk.dto.Login;
import api.helpdesk.dto.Session;
import api.helpdesk.security.JWTCreator;
import api.helpdesk.security.JWTObject;
import api.helpdesk.security.SecurityConfig;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private UserRepository userRepository;
    

    @PostMapping("/login")
    public Session login(@RequestBody Login login){
        User user = userRepository.findByUsername(login.getUsername());
        if(user!= null){
            boolean passwordOK = passwordEncoder.matches(login.getPassword(), user.getPassword());
            if(!passwordOK){
                throw new RuntimeException("Senha invalida para o login de usuario: " + login.getUsername());
            }

            Session session = new Session();
            session.setLogin(user.getUsername());


            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION));
            jwtObject.setRoles(user.getRoles());
            session.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return session;
        } else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}
