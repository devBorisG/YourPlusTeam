package com.uco.yourplus.apiyourplus.controller.persona;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.facade.persona.ConsultarPersonasFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa un controlador REST para consultar personas en el sistema YourPlus.
 * Permite realizar consultas de personas utilizando la fachada ConsultarPersonasFacade.
 *
 * @see ConsultarPersonasFacade
 * @author David Andrés
 */
@RestController
@RequestMapping("apiyourplus/persona")
public class ConsultarPersonasController {

    @Autowired
    private ConsultarPersonasFacade facade;

    /**
     * Endpoint para consultar personas.
     *
     * @param personaDTO El objeto PersonaDTO que contiene los parámetros de la consulta.
     * @return Una ResponseEntity que incluye un objeto Response con los resultados de la consulta.
     */
    @GetMapping("/consultarpersonas")
    public ResponseEntity<Response<PersonaDTO>> execute(@RequestBody PersonaDTO personaDTO){
        final Response<PersonaDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try{
            facade.execute(personaDTO);
            List<PersonaDTO> data = new ArrayList<>();
            data.add(personaDTO);
            response.addSuccesMessage("Lista de personas");
            response.setData(data);
        }catch (final YourPlusCustomException yourPlusCustomException){
            httpStatus = HttpStatus.BAD_REQUEST;
            if(yourPlusCustomException.isTechnicalException()){
                response.addErrorMessage("Ocurrio un error consultando, intente nuevamente");
            }else {
                response.addErrorMessage(yourPlusCustomException.getMessage());
            }
        }catch (final Exception exception){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error interno en el servidor, intente nuevamente");
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}
