package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.specification.persona.PersonaSpecification;
import com.uco.yourplus.serviceyourplus.usecase.persona.RegistrarPersona;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarPersonaImpl implements RegistrarPersona {

    @Autowired
    private PersonaRepository repository;

    @Autowired
    PersonaSpecification specification;

    @Override
    public void execute(PersonaDomain domain) {
        try{
            specification.isSatisfied(domain);
            PersonaEntity personaEntity = new PersonaEntity();
            BeanUtils.copyProperties(domain,personaEntity);
            repository.save(personaEntity);
            System.out.println("Se registro");
        }catch (RepositoryCustomException exception){
            throw ServiceCustomException.createTechnicalException(exception,"No se ha logrado ingresar con exito al nuevo usuario, por favor intente de nuevo");
        }catch (Exception e){
            throw ServiceCustomException.createTechnicalException(e,"Error");
        }
    }
}
