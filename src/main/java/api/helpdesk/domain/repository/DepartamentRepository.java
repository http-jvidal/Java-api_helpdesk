package api.helpdesk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.helpdesk.domain.models.Departament;

public interface DepartamentRepository  extends JpaRepository<Departament, Long>{
    boolean existsById(Long id);
} 
