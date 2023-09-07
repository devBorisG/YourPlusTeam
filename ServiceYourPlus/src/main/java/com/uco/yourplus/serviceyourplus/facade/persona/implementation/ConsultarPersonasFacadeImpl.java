package com.uco.yourplus.serviceyourplus.facade.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.facade.persona.ConsultarPersonasFacade;
import com.uco.yourplus.serviceyourplus.usecase.persona.ConsultarPersonas;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Esta clase representa la implementación de la fachada para consultar personas.
 * Proporciona un método para ejecutar la consulta de una persona por medio de un DTO.
 *
 * @author David Andrés
 */
@Service
public class ConsultarPersonasFacadeImpl implements ConsultarPersonasFacade {

    @Autowired
    private ConsultarPersonas consultarPersonas;

    /**
     * Ejecuta la consulta de una persona utilizando un objeto PersonaDTO.
     *
     * @param dto El objeto PersonaDTO que contiene los datos de la persona a consultar.
     * @throws ServiceCustomException Sí ocurre un error personalizado en el servicio.
     */
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
