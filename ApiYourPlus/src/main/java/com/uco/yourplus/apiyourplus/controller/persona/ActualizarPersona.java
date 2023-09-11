package com.uco.yourplus.apiyourplus.controller.persona;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.facade.persona.ActualizarPersonaFacade;
import com.uco.yourplus.serviceyourplus.specification.Specification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("apiyourplus/persona")
public class ActualizarPersona {

    @Autowired
    private ActualizarPersonaFacade facade;

    @Autowired
    private Specification<PersonaDTO> personaSpecification;

    @PutMapping("/{id}")
    public ResponseEntity<Response<PersonaDTO>> actualizarPersona(@PathVariable UUID id, @RequestBody PersonaDTO personaDTO) {
        final Response<PersonaDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            personaSpecification.isSatisfied(personaDTO);
            facade.execute(id, personaDTO);
            response.addSuccesMessage("Persona actualizada");
        } catch (final YourPlusCustomException exception) {
            httpStatus = HttpStatus.BAD_REQUEST;
            if (exception.isTechnicalException()) {
                response.addErrorMessage("Ocurrió un error técnico, intente nuevamente.");
            } else {
                response.addErrorMessage(exception.getMessage());
            }
        } catch (final Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrió un error del servidor, intenta de nuevo en unos segundos.");
        }

        return new ResponseEntity<>(response, httpStatus);
    }
}