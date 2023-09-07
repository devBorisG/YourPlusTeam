package com.uco.yourplus.apiyourplus.controller.persona;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.dtoyourplus.builder.PersonaDTO;
import com.uco.yourplus.serviceyourplus.facade.persona.ActualizarPersonaPatchFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apiyourplus/persona")
public class ActualizarPersonaPatch {

    @Autowired
    private ActualizarPersonaPatchFacade facade;

    @PatchMapping("{id}")
    ResponseEntity<Response<PersonaDTO>> execute(@PathVariable UUID id, @RequestBody Map<Object, Object> fields){
        final Response<PersonaDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try{

        }
    }
}
