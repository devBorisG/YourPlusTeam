package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ActualizarPersonaImpl implements ActualizarPersona {

    @Autowired
    private PersonaRepository repository;

    @Override
    public void execute(UUID id, PersonaEntity updatedPersona) {
        try {
            Optional<PersonaEntity> personaEntityOptional = repository.findById(id);
            if (personaEntityOptional.isPresent()) {
                PersonaEntity personaEntity = personaEntityOptional.get();
                personaEntity.setNombre(updatedPersona.getNombre());
                personaEntity.setApellido(updatedPersona.getApellido());
                personaEntity.setCorreo(updatedPersona.getCorreo());
                personaEntity.setPassword(updatedPersona.getPassword());
                repository.save(personaEntity);
            } else {
                throw new EntityNotFoundException("El usuario no existe");
            }
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error inesperado al actualizar la entidad: " + exception.getMessage());
        }
    }
}