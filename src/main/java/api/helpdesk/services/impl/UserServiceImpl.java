package api.helpdesk.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.helpdesk.domain.models.Departament;
import api.helpdesk.domain.models.User;
import api.helpdesk.domain.models.dto.Login;
import api.helpdesk.domain.models.dto.Sessao;
import api.helpdesk.domain.repository.DepartamentRepository;
import api.helpdesk.domain.repository.UserRepository;
import api.helpdesk.security.JWTCreator;
import api.helpdesk.security.JWTObject;
import api.helpdesk.security.SecurityConfig;
import api.helpdesk.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final SecurityConfig securityConfig;
    private final DepartamentRepository departamentRepository;
    

    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, DepartamentRepository departamentRepository, PasswordEncoder passwordEncoder, SecurityConfig securityConfig) {
        this.userRepository = userRepository;
        this.securityConfig = securityConfig;
        this.departamentRepository = departamentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findById(Long id){
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("Id Not Found");
        } else {
            Optional<User> user  = userRepository.findById(id);
            return user;
        }
    }

    public void SaveUserWithEncrypt(User user) {
        try {
            Departament existingDepartament = departamentRepository.findByName(user.getDepartamento().getName());
            if (existingDepartament == null) {
                // Criar novo departamento
                Departament newDepartament = new Departament();
                newDepartament.setName(user.getDepartamento().getName());
                departamentRepository.save(newDepartament);
    
                user.setDepartamento(newDepartament);
            } else {
                user.setDepartamento(existingDepartament);
            }
    
            saveUser(user);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    public void saveUser(User user) {
        User userExist = userRepository.findByUsername(user.getUsername());
        
        if (userExist == null) {
            // Novo usuário, codifica a senha e salva
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
    
            userRepository.save(user);
            System.out.println("Usuário criado com sucesso ");
        } else {
            throw new RuntimeException(user.getUsername() + " já existe ");
        }
    }
    
    public List<User> findAll(){
        List<User> res = userRepository.findAll();
        return res;
        
    }

    @Override
    public User findByName(String name) {
        User username = userRepository.findByName(name);
        return username;
    }

    @Override
    public List<User> findByNameContainingIgnoreCase(String name) {
        if(!userRepository.existsByName(name)){
            throw new IllegalArgumentException("This user not exists");
        } else {
            List<User> res = userRepository.findByNameContainingIgnoreCase(name);
            return res;
        }
        
    }

    @Override
    public void delete(Long id) {
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("This Id not exists");
        } else {
            userRepository.deleteById(id);
        }
    }

    @Override
    public User update(User user) {
        Optional<User> userId  = userRepository.findById(user.getId());
        if(!userId.isPresent()){
            throw new IllegalArgumentException("User not exists");
        } else {
            final User userUpdate = userRepository.save(user);
            return userUpdate;
        }
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }


    @Override
    public boolean isValidUser(String username, String password) {
            User user = userRepository.findByUsername(username);
            return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public Sessao Authenticate(Login login) {
        if (login == null || login.getUsername() == null || login.getPassword() == null) {
                throw new IllegalArgumentException("Login ou senha inválidos");
        }

        User user = userRepository.findByUsername(login.getUsername().toString());

        if (user != null) {
                if (this.isValidUser(login.getUsername().toString(), login.getPassword().toString())) {
                // Criação do token JWT
                JWTObject jwtObject = new JWTObject();
                jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
                jwtObject.setExpiration(new Date(System.currentTimeMillis() + securityConfig.getEXPIRATION()));
                jwtObject.setRoles(user.getRoles());

                String token = JWTCreator.create(securityConfig.getPREFIX(), securityConfig.getKEY(), jwtObject);

                // Criando e retornando a sessão com o token
                Sessao sessao = new Sessao();
                sessao.setLogin(user.getUsername());
                sessao.setToken(token);

                System.out.println(token);
                return sessao;

                } else {
                throw new RuntimeException("Senha incorreta para o usuário: " + login.getUsername());
                }
        } else {
                throw new RuntimeException("Usuário não encontrado para o login: " + login.getUsername());
        }
    }

    

}
