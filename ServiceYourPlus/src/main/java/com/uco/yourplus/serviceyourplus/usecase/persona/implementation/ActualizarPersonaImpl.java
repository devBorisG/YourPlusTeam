package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.serviceyourplus.domain.PersonaDomain;
import com.uco.yourplus.serviceyourplus.usecase.persona.ActualizarPersona;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActualizarPersonaImpl implements ActualizarPersona {

    @Autowired
    private PersonaRepository repository;

    @Override
    public void execute(UUID id, PersonaDomain updatedPersona) {
        try {
            Optional<PersonaEntity> personaEntityOptional = repository.findById(id);
            if (personaEntityOptional.isPresent()) {
                PersonaEntity personaEntity = personaEntityOptional.get();
                BeanUtils.copyProperties(updatedPersona, personaEntity);
                repository.save(personaEntity);
            } else {
                throw new EntityNotFoundException("El usuario no existe");
            }
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error inesperado al actualizar la entidad: " + exception.getMessage());
        }
    }
}