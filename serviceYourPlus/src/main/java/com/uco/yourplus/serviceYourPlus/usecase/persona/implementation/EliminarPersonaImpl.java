package com.uco.yourplus.serviceYourPlus.usecase.persona.implementation;

import com.uco.yourplus.entityYourPlus.PersonaEntity;
import com.uco.yourplus.repositoryYourPlus.persona.PersonaRepository;
import com.uco.yourplus.serviceYourPlus.domain.PersonaDomain;
import com.uco.yourplus.serviceYourPlus.specification.persona.PersonaSpecification;
import com.uco.yourplus.serviceYourPlus.usecase.persona.EliminarPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EliminarPersonaImpl implements EliminarPersona {

    @Autowired
    private PersonaRepository repository;
    @Autowired
    PersonaSpecification specification;


    @Override
    public void eliminarPersona(UUID id) {
        repository.deleteById(id);
    }
}
