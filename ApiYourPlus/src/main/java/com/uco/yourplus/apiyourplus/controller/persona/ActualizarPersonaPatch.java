package com.uco.yourplus.apiyourplus.controller.persona;

import com.github.fge.jsonpatch.JsonPatch;
import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.facade.persona.ActualizarPersonaPatchFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controlador REST para la actualización parcial de una entidad "Persona" utilizando JsonPatch.
 * Permite aplicar operaciones de parcheo a una entidad "Persona" identificada por su ID.
 */
@RestController
@RequestMapping("apiyourplus/persona")
public class ActualizarPersonaPatch {

    @Autowired
    private ActualizarPersonaPatchFacade facade;

    /**
     * Realiza la operación de actualización parcial de una entidad "Persona" mediante JsonPatch.
     *
     * @param id    El ID único que identifica la entidad "Persona" a ser actualizada.
     * @param patch El objeto JsonPatch que contiene las operaciones de parcheo a aplicar.
     * @return ResponseEntity que contiene una respuesta con detalles sobre el resultado de la operación.
     */
    @PatchMapping("/{id}")
    ResponseEntity<Response<PersonaDTO>> execute(@PathVariable UUID id, @RequestBody JsonPatch patch) {
        final Response<PersonaDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            facade.execute(id, patch);
            response.addSuccesMessage("Persona actualizada");
        } catch (final YourPlusCustomException exception) {
            httpStatus = HttpStatus.BAD_REQUEST;
            if (exception.isTechnicalException()) {
                response.addErrorMessage("Ocurrio un error, intente nuevamente");
            } else {
                response.addErrorMessage(exception.getMessage());
            }
        } catch (final Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error del servidor, intenta dentro de unos segundo");
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}
