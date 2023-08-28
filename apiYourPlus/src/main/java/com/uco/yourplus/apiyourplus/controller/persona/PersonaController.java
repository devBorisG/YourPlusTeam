package com.uco.yourplus.apiyourplus.controller.persona;

import com.uco.yourplus.dtoYourPlus.builder.PersonaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("http://www.yourplus.com/persona")
public class PersonaController {
    Random random = new Random();
    @GetMapping
    public String mostrarPersona(@RequestParam("id") String id){
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

    @PostMapping
    public String ingresarPersona(){
        return "Persona registrado correctamente";
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
