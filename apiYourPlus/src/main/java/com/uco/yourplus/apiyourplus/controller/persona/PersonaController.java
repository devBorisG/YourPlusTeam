package com.uco.yourplus.apiyourplus.controller.persona;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingYourPlus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoYourPlus.builder.PersonaDTO;
import com.uco.yourplus.serviceYourPlus.facade.persona.RegistrarPersonaFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("apiyourplus/persona")
public class PersonaController {

    @Autowired
    RegistrarPersonaFacade registrarPersonaFacade;

    Random random = new Random();
    @GetMapping
    public String mostrarPersona(@RequestParam("id") String id){
        final Response<PersonaDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try{
            //code
        }catch (Exception e){
            //code
        }
        return String.format("Producto con id: %s", id);
    }

    @GetMapping("/all")
    public List<Integer> mostrarPersonas(){
        List<Integer> aleatorio = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            aleatorio.add(random.nextInt());
        }
        return aleatorio;
    }

    @PutMapping
    public String actualizarPersona(@RequestParam("id") String id){
        return String.format("La Persona con id %s ha sido modificado",id);
    }

    @PatchMapping
    public String actualizarParametroPersona(@RequestParam("id") String id){
        return "se ha actualizado el parametro correctamente";
    }

    @DeleteMapping
    public String eliminarPersona(@RequestParam("id") String id){
        return "se ha eliminado la Persona correctamente";
    }
}
