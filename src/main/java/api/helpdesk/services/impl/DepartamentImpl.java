package api.helpdesk.services.impl;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.stereotype.Service;

import api.helpdesk.domain.models.Departament;
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



   

    
