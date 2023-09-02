package com.uco.yourplus.serviceYourPlus.facade.persona.implementation;

import com.uco.yourplus.crosscuttingYourPlus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoYourPlus.builder.PersonaDTO;
import com.uco.yourplus.serviceYourPlus.domain.PersonaDomain;
import com.uco.yourplus.serviceYourPlus.facade.persona.ConsultarPersonaFacade;
import com.uco.yourplus.serviceYourPlus.usecase.persona.ConsultarPersona;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultarPersonaFacadeImpl implements ConsultarPersonaFacade {

    @Autowired
    private ConsultarPersona consultarPersona;

    @Override
    public void execute(PersonaDTO dto) {
        PersonaDomain domain = new PersonaDomain();
        BeanUtils.copyProperties(dto, domain);
        try{
            consultarPersona.execute(domain);
        }catch (ServiceCustomException e){
            throw ServiceCustomException.createTechnicalException(e, e.getMessage());
        }catch (Exception e){
            throw  ServiceCustomException.createTechnicalException(e, "Ocurrio un error al entrar al caso de uso");
        }
    }
}
