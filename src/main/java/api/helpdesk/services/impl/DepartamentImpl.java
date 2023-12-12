package api.helpdesk.services.impl;

import org.springframework.stereotype.Service;

import api.helpdesk.domain.repository.DepartamentRepository;
import api.helpdesk.services.DepartamentService;

@Service
public class DepartamentImpl implements DepartamentService{
    
    private final DepartamentRepository departamentRepository;

    public DepartamentImpl(DepartamentRepository departamentRepository) {
        this.departamentRepository = departamentRepository;
    }

    @Override
    public void delete(Long id) {
        if(departamentRepository.existsById(id)){
            departamentRepository.deleteById(id);
        }
    }

    
}
