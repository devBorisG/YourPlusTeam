package com.uco.yourplus.serviceYourPlus.facade.persona.implementation;

import com.uco.yourplus.dtoYourPlus.builder.PersonaDTO;
import com.uco.yourplus.repositoryYourPlus.persona.PersonaRepository;
import com.uco.yourplus.serviceYourPlus.facade.persona.EliminarPersonaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EliminarPersonaFacadeImpl implements EliminarPersonaFacade {
    @Autowired
    private PersonaRepository repository;

    @Override
    public void eliminarPersona(UUID id) {
        repository.deleteById(id);
    }
}
