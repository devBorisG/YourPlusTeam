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


// Define el paquete y el nombre de la clase
@RestController
@RequestMapping("apiyourplus/persona")
public class ActualizarPersona {

    // Inyecta la dependencia de ActualizarPersonaFacade
    @Autowired
    private ActualizarPersonaFacade facade;

    // Inyecta la dependencia de Specification parametrizada con PersonaDTO
    @Autowired
    private Specification<PersonaDTO> personaSpecification;

    // Define un método para manejar solicitudes PUT en la ruta "/apiyourplus/persona/{id}"
    @PutMapping("/{id}")
    public ResponseEntity<Response<PersonaDTO>> actualizarPersona(@PathVariable UUID id, @RequestBody PersonaDTO personaDTO) {
        // Crea una instancia de Response para manejar la respuesta
        final Response<PersonaDTO> response = new Response<>();
        // Establece el estado HTTP inicial como OK
        HttpStatus httpStatus = HttpStatus.OK;

        try {
            // Verifica si la especificación de persona se cumple
            personaSpecification.isSatisfied(personaDTO);

            // Ejecuta la actualización de la persona a través de la fachada
            facade.execute(id, personaDTO);

            // Agrega un mensaje de éxito a la respuesta
            response.addSuccesMessage("Persona actualizada");
        } catch (final YourPlusCustomException exception) {
            // Maneja una excepción personalizada
            httpStatus = HttpStatus.BAD_REQUEST;
            if (exception.isTechnicalException()) {
                // Si la excepción es de tipo técnico, agrega un mensaje de error técnico
                response.addErrorMessage("Ocurrió un error técnico, intente nuevamente.");
            } else {
                // Si no es de tipo técnico, agrega el mensaje de la excepción
                response.addErrorMessage(exception.getMessage());
            }
        } catch (final Exception exception) {
            // Maneja excepciones
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            // Agrega un mensaje de error fatal a la respuesta
            response.addFatalMessage("Ocurrió un error del servidor, intenta de nuevo en unos segundos.");
        }

        // Devuelve una ResponseEntity que contiene la respuesta y el estado HTTP correspondiente
        return new ResponseEntity<>(response, httpStatus);
    }
}