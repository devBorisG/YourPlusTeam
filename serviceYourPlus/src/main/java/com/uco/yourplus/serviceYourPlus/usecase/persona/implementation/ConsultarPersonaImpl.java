package com.uco.yourplus.serviceYourPlus.usecase.persona.implementation;

import com.uco.yourplus.entityYourPlus.PersonaEntity;
import com.uco.yourplus.repositoryYourPlus.persona.PersonaRepository;
import com.uco.yourplus.serviceYourPlus.domain.PersonaDomain;
import com.uco.yourplus.serviceYourPlus.usecase.persona.ConsultarPersona;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultarPersonaImpl implements ConsultarPersona {

    @Autowired
    private PersonaRepository repository;

    @Override
    public PersonaDomain execute(PersonaDomain domain) {
        PersonaEntity result;
        PersonaEntity entity = new PersonaEntity();
        BeanUtils.copyProperties(domain, entity);
    }
}
