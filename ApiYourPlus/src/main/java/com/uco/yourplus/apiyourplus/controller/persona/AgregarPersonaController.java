package com.uco.yourplus.apiyourplus.controller.persona;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.facade.persona.RegistrarPersonaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("apiyourplus/persona")
public class AgregarPersonaController {

    @Autowired
    RegistrarPersonaFacade registrarPersonaFacade;
    Random random = new Random();

    @PostMapping("/registrar")
    public ResponseEntity<Response<PersonaDTO>> execute(@RequestBody PersonaDTO personaDTO){
        final Response<PersonaDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try{
            registrarPersonaFacade.execute(personaDTO);
            List<PersonaDTO> data = new ArrayList<>();
            data.add(personaDTO);
            response.addSuccesMessage("melito mi rey");
            response.setData(data);
        } catch(final YourPlusCustomException yourPlusCustomException){
            httpStatus = HttpStatus.BAD_REQUEST;
            if(yourPlusCustomException.isTechnicalException()){
                response.addErrorMessage("c daño el sistema");
            } else{
                response.addErrorMessage(yourPlusCustomException.getMessage());
            }
        } catch(final Exception exception){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("apague y vuelva a prender que c murio el sistema");
        }
        return new ResponseEntity<>(response,httpStatus);
    }
}
