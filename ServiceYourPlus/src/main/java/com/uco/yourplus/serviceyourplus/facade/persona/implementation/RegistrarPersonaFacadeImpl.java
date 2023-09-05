package com.uco.yourplus.serviceyourplus.facade.persona.implementation;

import com.uco.yourplus.dtoYourPlus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.facade.persona.RegistrarPersonaFacade;
import org.springframework.beans.BeanUtils;
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
