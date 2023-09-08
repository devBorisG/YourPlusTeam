package com.uco.yourplus.serviceyourplus.usecase.persona.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.entityyourplus.PersonaEntity;
import com.uco.yourplus.repositoryyourplus.persona.PersonaRepository;
import com.uco.yourplus.serviceyourplus.usecase.persona.ActualizarPersonaPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementación del caso de uso para la actualización parcial de entidades "Persona"
 * utilizando operaciones de parcheo JsonPatch.
 * <p>
 * Esta implementación se comunica con el repositorio de entidades "Persona" y maneja las excepciones
 * relacionadas con la ejecución del caso de uso.
 */
@Service
public class ActualizarPersonaPatchImpl implements ActualizarPersonaPatch {

    @Autowired
    private PersonaRepository repository;

    /**
     * Ejecuta el caso de uso para aplicar operaciones de parcheo JsonPatch a una entidad "Persona"
     * identificada por su UUID.
     *
     * @param id    El UUID que identifica la entidad "Persona" a ser parcheada.
     * @param patch El objeto JsonPatch que contiene las operaciones de parcheo a aplicar.
     * @throws ServiceCustomException Sí ocurre un error durante la ejecución del caso de uso.
     */
    @Override
    public void execute(UUID id, JsonPatch patch) {
        try {
            Optional<PersonaEntity> personaEntityOptional = repository.findById(id);
            if (personaEntityOptional.isPresent()) {
                PersonaEntity personaEntity = personaEntityOptional.get();
                PersonaEntity personaPatch = applyPatchToPersona(patch, personaEntity);
                repository.save(personaPatch);
            }
        } catch (EntityNotFoundException exception) {
            throw ServiceCustomException.createUserException("El usuario no exisxte");
        } catch (ServiceCustomException exception) {
            throw exception;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado al actualizar la entidad: " + exception.getMessage());
        }
    }

    /**
     * Aplica operaciones de parcheo JsonPatch a una entidad "Persona" y devuelve la entidad parcheada.
     *
     * @param patch         El objeto JsonPatch que contiene las operaciones de parcheo.
     * @param targetPersona La entidad "Persona" a la que se aplicarán las operaciones de parcheo.
     * @return La entidad "Persona" parcheada después de aplicar las operaciones de parcheo.
     * @throws ServiceCustomException Sí ocurre un error durante la aplicación de las operaciones de parcheo.
     */
    private PersonaEntity applyPatchToPersona(JsonPatch patch, PersonaEntity targetPersona) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode targetJsonNode = objectMapper.valueToTree(targetPersona);
            JsonNode patched = patch.apply(targetJsonNode);
            return objectMapper.treeToValue(patched, PersonaEntity.class);
        } catch (JsonPatchException | IllegalArgumentException | IOException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error intentando aplicar el patch a la entidad: " + exception.getMessage());
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado: " + exception.getMessage());
        }
    }
}
