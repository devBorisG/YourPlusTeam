package com.uco.yourplus.serviceyourplus.facade.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.facade.persona.ActualizarPersonaFacade;
import com.uco.yourplus.serviceyourplus.usecase.persona.ActualizarPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActualizarPersonaFacadeImpl implements ActualizarPersonaFacade {

    @Autowired
    private ActualizarPersona useCase;

    @Override
    public void execute(UUID id, PersonaEntity personaEntity) {
        try {
            useCase.execute(id, personaEntity);
        } catch (ServiceCustomException exception) {
            throw exception;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error intentando ejecutar el caso de uso: " + exception.getMessage());
        }
    }
}