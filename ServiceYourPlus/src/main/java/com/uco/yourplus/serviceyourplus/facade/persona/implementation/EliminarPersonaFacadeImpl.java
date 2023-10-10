package com.uco.yourplus.serviceyourplus.facade.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.facade.persona.EliminarPersonaFacade;
import com.uco.yourplus.serviceyourplus.usecase.persona.EliminarPersona;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EliminarPersonaFacadeImpl implements EliminarPersonaFacade {
    @Autowired
    private EliminarPersona eliminarPersona;

    @Override
    public void execute(PersonaDTO dto) {
        try {
            PersonaDomain personaDomain = new PersonaDomain();
            BeanUtils.copyProperties(dto, personaDomain);
            eliminarPersona.execute(personaDomain);
        } catch (ServiceCustomException serviceCustomException) {
            throw serviceCustomException;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error eliminando la persona" + exception.getMessage());
        }
    }
}
