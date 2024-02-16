package api.helpdesk.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import api.helpdesk.domain.models.Departament;

public interface DepartamentRepository  extends JpaRepository<Departament, Long>{
    boolean existsById(Long id);

    boolean existsByName(String name);

    Departament findByName(String name);
} 
