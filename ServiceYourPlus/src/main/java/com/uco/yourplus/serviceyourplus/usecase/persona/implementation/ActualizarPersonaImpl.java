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

// Esta clase es una implementación del servicio de ActualizarPersona
// que actualiza una entidad Persona en el repositorio.
@Service
public class ActualizarPersonaImpl implements ActualizarPersona {

    // Se inyecta el repositorio PersonaRepository mediante Autowired.
    @Autowired
    private PersonaRepository repository;

    // Implementación del método execute de la interfaz ActualizarPersona.
    @Override
    public void execute(UUID id, PersonaDomain updatedPersona) {
        try {
            // Intenta encontrar una PersonaEntity en el repositorio utilizando su ID.
            Optional<PersonaEntity> personaEntityOptional = repository.findById(id);

            if (personaEntityOptional.isPresent()) {
                // Si se encuentra la entidad, obténla y actualiza sus propiedades con los datos de updatedPersona.
                PersonaEntity personaEntity = personaEntityOptional.get();
                BeanUtils.copyProperties(updatedPersona, personaEntity);
                // Guarda la entidad actualizada en el repositorio.
                repository.save(personaEntity);
            } else {
                // Si no se encuentra la entidad, lanza una excepción EntityNotFoundException.
                throw new EntityNotFoundException("El usuario no existe");
            }
        } catch (Exception exception) {
            // Si ocurre una excepción, maneja y lanza una excepción personalizada ServiceCustomException.
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error inesperado al actualizar la entidad: " + exception.getMessage());
        }
    }
}