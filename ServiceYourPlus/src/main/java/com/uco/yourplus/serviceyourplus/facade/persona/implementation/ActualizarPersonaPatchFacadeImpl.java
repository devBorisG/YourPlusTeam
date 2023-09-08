package com.uco.yourplus.serviceyourplus.facade.persona.implementation;

import com.github.fge.jsonpatch.JsonPatch;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.facade.persona.ActualizarPersonaPatchFacade;
import com.uco.yourplus.serviceyourplus.usecase.persona.ActualizarPersonaPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Implementación de la interfaz ActualizarPersonaPatchFacade que se encarga de ejecutar casos de uso
 * para la actualización parcial de entidades "Persona" utilizando operaciones de parcheo JsonPatch.
 * <p>
 * Esta implementación se comunica con el caso de uso correspondiente (ActualizarPersonaPatch) y maneja las excepciones
 * relacionadas con la ejecución de los casos de uso.
 *
 * @see ActualizarPersonaPatchFacade
 */
@Service
public class ActualizarPersonaPatchFacadeImpl implements ActualizarPersonaPatchFacade {

    @Autowired
    private ActualizarPersonaPatch useCase;

    /**
     * Ejecuta un caso de uso para aplicar operaciones de parcheo JsonPatch a una entidad "Persona" identificada por su UUID.
     *
     * @param id    El UUID que identifica la entidad "Persona" a ser parcheada.
     * @param patch El objeto JsonPatch que contiene las operaciones de parcheo a aplicar.
     * @throws ServiceCustomException Sí ocurre un error durante la ejecución del caso de uso.
     */
    @Override
    public void execute(UUID id, JsonPatch patch) {
        try {
            useCase.execute(id, patch);
        } catch (ServiceCustomException exception) {
            throw exception;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error intentando ejecutar el caso de uso: " + exception.getMessage());
        }
    }
}
