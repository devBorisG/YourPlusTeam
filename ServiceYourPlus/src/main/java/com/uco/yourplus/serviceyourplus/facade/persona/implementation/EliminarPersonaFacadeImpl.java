package com.uco.yourplus.serviceyourplus.facade.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.facade.persona.EliminarPersonaFacade;
import com.uco.yourplus.serviceyourplus.usecase.persona.ConsultarPersonas;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EliminarPersonaFacadeImpl implements EliminarPersonaFacade {
    @Autowired
    private PersonaRepository repository;
    @Autowired
    private ConsultarPersonas consultarPersonas;


    @Override
    public void execute(PersonaDTO dto) {
        try{
            PersonaDomain personaDomain = new PersonaDomain();
            BeanUtils.copyProperties(dto,personaDomain);
            consultarPersonas.execute(Optional.of(personaDomain));
        } catch(ServiceCustomException serviceCustomException){
            throw serviceCustomException;
        } catch(Exception exception){
            throw exception;
        }
    }
}
