package com.uco.yourplus.serviceyourplus.facade.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.domain.RolDomain;
import com.uco.yourplus.serviceyourplus.facade.persona.RegistrarPersonaFacade;
import com.uco.yourplus.serviceyourplus.usecase.persona.RegistrarPersona;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegistrarPersonaFacadeImpl implements RegistrarPersonaFacade {

    private final RegistrarPersona registrarPersona;

    public RegistrarPersonaFacadeImpl(RegistrarPersona registrarPersona) {
        this.registrarPersona = registrarPersona;
    }

    @Override
    public void execute(PersonaDTO dto) {
        try {
            PersonaDomain personaDomain = new PersonaDomain();
            RolDomain rolDomain = new RolDomain();
            BeanUtils.copyProperties(dto, personaDomain);
            BeanUtils.copyProperties(dto.getRolDTO(), rolDomain);
            personaDomain.setRolDomain(rolDomain);
            registrarPersona.execute(personaDomain);
        } catch (ServiceCustomException serviceCustomException) {
            throw serviceCustomException;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error registrando la persona");
        }
    }
}
