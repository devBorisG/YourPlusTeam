package com.uco.yourplus.serviceyourplus.facade.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.facade.persona.RegistrarPersonaFacade;
import com.uco.yourplus.serviceyourplus.usecase.persona.RegistrarPersona;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RegistrarPersonaFacadeImpl implements RegistrarPersonaFacade {

    @Autowired
    private RegistrarPersona registrarPersona;

    @Override
    public String execute(PersonaDTO dto) {
        try{
            PersonaDomain personaDomain = new PersonaDomain();
            BeanUtils.copyProperties(dto, personaDomain);
            return registrarPersona.execute(personaDomain);
        } catch(ServiceCustomException serviceCustomException){
            throw serviceCustomException;
        } catch(Exception exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ocurrio un error registrando la persona");
        }
    }
}
