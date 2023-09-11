package com.uco.yourplus.apiyourplus.controller.persona;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.facade.persona.EliminarPersonaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("apiyourplus/persona")
public class EliminarPersonaController {

    @Autowired
    private EliminarPersonaFacade facade;

    @DeleteMapping()
    public ResponseEntity<Response<PersonaDTO>>execute(@RequestBody PersonaDTO personaDTO){
        final Response<PersonaDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try{
            facade.execute(personaDTO);
            List<PersonaDTO> data = new ArrayList<>();
            data.add(personaDTO);
            response.addSuccesMessage("Se ha consultado correctamente para eliminar");
            response.setData(data);
        }catch(final YourPlusCustomException yourPlusCustomException){
            httpStatus = HttpStatus.BAD_REQUEST;
            if(yourPlusCustomException.isTechnicalException()){
                response.addErrorMessage("Ocurrio un error eliminando, intente de nuevo");
            } else{
                response.addErrorMessage(yourPlusCustomException.getMessage());
            }
        } catch (final Exception exception){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addErrorMessage("Ocurrio un problema con el servidor, intente nuevamente");
        }
        return new ResponseEntity<>(response,httpStatus);
    }
}
