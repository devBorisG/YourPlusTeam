package com.uco.yourplus.serviceYourPlus.facade.persona.implementation;

import com.uco.yourplus.dtoYourPlus.builder.PersonaDTO;
import com.uco.yourplus.entityYourPlus.PersonaEntity;
import com.uco.yourplus.serviceYourPlus.domain.PersonaDomain;
import com.uco.yourplus.serviceYourPlus.facade.persona.RegistrarPersonaFacade;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrarPersonaFacadeImpl implements RegistrarPersonaFacade {

    @Override
    public void execute(PersonaDTO dto) {
        PersonaDomain personaDomain = new PersonaDomain();
        BeanUtils.copyProperties(dto, personaDomain);
        System.out.println(personaDomain.getNombre());
    }
}
