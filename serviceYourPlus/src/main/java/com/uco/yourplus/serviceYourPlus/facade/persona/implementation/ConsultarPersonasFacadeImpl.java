package com.uco.yourplus.serviceYourPlus.facade.persona.implementation;

import com.uco.yourplus.crosscuttingYourPlus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoYourPlus.builder.PersonaDTO;
import com.uco.yourplus.serviceYourPlus.domain.PersonaDomain;
import com.uco.yourplus.serviceYourPlus.facade.persona.ConsultarPersonasFacade;
import com.uco.yourplus.serviceYourPlus.usecase.persona.ConsultarPersonas;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultarPersonasFacadeImpl implements ConsultarPersonasFacade {

    @Autowired
    private ConsultarPersonas consultarPersonas;

    @Override
    public void execute(PersonaDTO dto) {
        try{
            PersonaDomain personaDomain = new PersonaDomain();
            BeanUtils.copyProperties(dto, personaDomain);
            consultarPersonas.execute(Optional.of(personaDomain));
        }catch (ServiceCustomException serviceCustomException){
            throw serviceCustomException;
        }catch (Exception exception){
            throw  ServiceCustomException.createTechnicalException("Ocurrio un error intentando implementar el caso de uso de registrar usuarios"+":"+exception.getMessage());
        }
    }
}
