package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.usecase.persona.ConsultarPersona;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultarPersonaImpl implements ConsultarPersona {

    @Autowired
    private PersonaRepository repository;

    @Override
    public PersonaDomain execute(PersonaDomain domain) {
        PersonaEntity result;
        PersonaEntity entity = new PersonaEntity();
        BeanUtils.copyProperties(domain, entity);
        try {
            result = repository.findCustom(entity).get(0);
        }catch (Exception e){
            throw ServiceCustomException.createTechnicalException(e, "Ocurrio un error consultando el usuario");
        }
        BeanUtils.copyProperties(result, domain);
        return domain;
    }
}
