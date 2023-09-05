package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.repository.RepositoryCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.specification.persona.PersonaDeleteSpecification;
import com.uco.yourplus.serviceyourplus.usecase.persona.ConsultarPersonas;
import com.uco.yourplus.serviceyourplus.usecase.persona.EliminarPersona;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EliminarPersonaImpl implements EliminarPersona {

    @Autowired
    private PersonaRepository repository;
    @Autowired
    private ConsultarPersonas consultarPersonas;
    @Autowired
    private PersonaDeleteSpecification personaDeleteSpecification;

    @Override
    public void execute(PersonaDomain domain) {
        try{
            personaDeleteSpecification.isSatisfied(domain);
            PersonaDomain consulta = new PersonaDomain();
            consulta = consultarPersonas.execute(Optional.of(domain)).get(0);
            PersonaEntity personaEntity = new PersonaEntity();
            BeanUtils.copyProperties(domain,personaEntity);
            repository.delete(personaEntity);
        } catch (ServiceCustomException exception){
            throw exception;
        } catch (RepositoryCustomException exception){
            throw ServiceCustomException.createTechnicalException("No se ha logrado consultar");
        } catch (Exception e){
            throw ServiceCustomException.createTechnicalException("Alv c murio");
        }
    }
}
