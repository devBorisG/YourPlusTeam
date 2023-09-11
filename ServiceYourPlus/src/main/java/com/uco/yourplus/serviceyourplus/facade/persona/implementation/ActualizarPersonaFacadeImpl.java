package com.uco.yourplus.serviceyourplus.facade.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.facade.persona.ActualizarPersonaFacade;
import com.uco.yourplus.serviceyourplus.usecase.persona.ActualizarPersona;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ActualizarPersonaFacadeImpl implements ActualizarPersonaFacade {

    @Autowired
    private ActualizarPersona useCase;

    @Override
    public void execute(UUID id, PersonaDTO personaDTO) {
        try {
            // Crea una instancia de PersonaDomain
            PersonaDomain personaDomain = new PersonaDomain();

            // Copia propiedades desde personaDTO a personaDomain
            BeanUtils.copyProperties(personaDTO, personaDomain);

            // Llama al método execute del objeto useCase, pasando el id y personaDomain
            useCase.execute(id, personaDomain);
        } catch (ServiceCustomException exception) {
            // Si ocurre una ServiceCustomException, se lanza nuevamente sin hacer nada adicional
            throw exception;
        } catch (Exception exception) {
            // Si ocurre una excepción
            // Crea una instancia de ServiceCustomException para encapsular la excepción original
            // Proporciona un mensaje personalizado y adjunta la excepción original como causa
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error intentando ejecutar el caso de uso: " + exception.getMessage());
        }
    }
}


