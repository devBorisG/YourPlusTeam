package com.uco.yourplus.apiyourplus.controller.persona;

import com.uco.yourplus.dtoYourPlus.builder.PersonaDTO;
import com.uco.yourplus.serviceYourPlus.facade.UseCaseFacadeDelete;
import com.uco.yourplus.serviceYourPlus.facade.persona.EliminarPersonaFacade;
import com.uco.yourplus.serviceYourPlus.usecase.UseCaseDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("apiyourplus/persona")
public class EliminarPersonaController {

    private final UseCaseFacadeDelete facade;

    @Autowired
    public EliminarPersonaController(UseCaseFacadeDelete facade) {
        this.facade = facade;
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePersona(@PathVariable UUID id){
        facade.eliminarPersona(id);
        return ResponseEntity.noContent().build();
    }

}
