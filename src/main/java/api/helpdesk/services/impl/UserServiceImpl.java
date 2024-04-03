package api.helpdesk.services.impl;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.helpdesk.domain.models.Departament;
import api.helpdesk.domain.models.User;
import api.helpdesk.domain.repository.DepartamentRepository;
import api.helpdesk.domain.repository.UserRepository;
import api.helpdesk.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    private final DepartamentRepository departamentRepository;
    


    public UserServiceImpl(UserRepository userRepository, DepartamentRepository departamentRepository) {
        this.userRepository = userRepository;
        this.departamentRepository = departamentRepository;
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

    public void saveUser(User user) {
        // Verificar se o departamento já existe
        Departament existingDepartament = departamentRepository.findByName(user.getDepartamento().getName());

        if (existingDepartament == null) {
            // Se o departamento não existe, crie um novo
            Departament newDepartament = new Departament();
            newDepartament.setName(user.getDepartamento().getName());
            // Salve o novo departamento no banco de dados
            departamentRepository.save(newDepartament);

            // Crie o usuário com o novo departamento
            userRepository.save(user).getId();
        } else {
            // Se o departamento já existe, crie o usuário com o departamento existente
            userRepository.save(user).getId();
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
        var user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User login(User user) {
        User userExists = userRepository.findByUsername(user.getUsername());
        if(userExists == null)
            throw new IllegalArgumentException("User not exists in database");
        return user;

    }

    @Override
    public String getUserRoles(User user) {
        return user.getRoles();
    }

   
    
}
