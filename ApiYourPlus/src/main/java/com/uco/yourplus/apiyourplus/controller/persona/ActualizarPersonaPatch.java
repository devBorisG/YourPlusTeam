package com.uco.yourplus.apiyourplus.controller.persona;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.facade.persona.ActualizarPersonaPatchFacade;
import com.uco.yourplus.serviceyourplus.facade.persona.ConsultarPersonasFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.uco.yourplus.dtoyourplus.builder.persona.PersonaDTOBuilder.getPersonaDTOBuilder;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("apiyourplus/persona")
public class ActualizarPersonaPatch {

    @Autowired
    private ActualizarPersonaPatchFacade facade;

    @Autowired
    private ConsultarPersonasFacade consultarPersonasFacade;

    @PatchMapping("/patch")
    ResponseEntity<Response<PersonaDTO>> execute(@RequestBody Map<String, Object> fields){
        final Response<PersonaDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try{
            facade.execute(fields);
            PersonaDTO personaDTO = getPersonaDTOBuilder().setId((UUID) fields.get("id")).build();
            consultarPersonasFacade.execute(personaDTO);
            List<PersonaDTO> data = new ArrayList<>();
            data.add(personaDTO);
            response.addSuccesMessage("Persona actualizada");
            response.setData(data);
        }catch (final YourPlusCustomException exception){
            httpStatus = HttpStatus.BAD_REQUEST;
            if (exception.isTechnicalException()){
                response.addErrorMessage("Ocurrio un error, intente nuevamente");
            }else {
                response.addErrorMessage(exception.getMessage());
            }
        }catch (final Exception exception){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error del servidor, intenta dentro de unos segundo");
        }
        return new ResponseEntity<>(response,httpStatus);
    }
}
