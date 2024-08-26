package api.helpdesk.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.helpdesk.domain.models.Departament;
import api.helpdesk.domain.models.User;
import api.helpdesk.domain.models.dto.Login;
import api.helpdesk.domain.models.dto.Sessao;
import api.helpdesk.domain.models.dto.UserDTO;
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
    public Optional<UserDTO> findById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Id Not Found");
        } else {
            return userRepository.findById(id).map(User::toDTO);
        }
    }


    public void saveUser(User user) {
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
    
                // Verifica se usuário existe
        User userExist = userRepository.findByUsername(user.getUsername());
            
            // Se não existir, cria um
        if (userExist == null) {
            // Novo usuário, codifica a senha e salva
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            userRepository.save(user);
            System.out.println("Usuário criado com sucesso ");
        } else {
            throw new RuntimeException(user.getUsername() + " já existe ");
        }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    
    
    public List<UserDTO> findAll(){
        List<User> users = userRepository.findAll();
        if(users == null){
            throw new IllegalArgumentException("Repositorio vázio");
        } else {
            return users.stream()
            .map(User::toDTO)
                    .collect(Collectors.toList());

        }
        
        
    }

    @Override
    public User findByName(String name) {
        User username = userRepository.findByName(name);
        return username;
    }

    @Override
    public List<UserDTO> findByNameContainingIgnoreCase(String name) {
        if(!userRepository.existsByName(name)){
            throw new IllegalArgumentException("This user not exists");
        } else {
            List<User> users = userRepository.findByNameContainingIgnoreCase(name);
            return users.stream()
                    .map(User::toDTO)
                    .collect(Collectors.toList());
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
    public UserDTO update(User user) {
        Optional<User> userId  = userRepository.findById(user.getId());
        if(!userId.isPresent()){
            throw new IllegalArgumentException("User not exists");
        } else {
            User userUpdate = userRepository.save(user);
            return userUpdate.toDTO();
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

                JWTObject jwtObject = new JWTObject();
                jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
                jwtObject.setExpiration(new Date(System.currentTimeMillis() + securityConfig.getEXPIRATION()));
                jwtObject.setRoles(user.getRoles());

                String token = JWTCreator.create(securityConfig.getPREFIX(), securityConfig.getKEY(), jwtObject);

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
