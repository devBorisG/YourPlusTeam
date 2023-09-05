package com.uco.yourplus.apiyourplus.controller.rol;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("http://www.yourplus.com/rol")
public class RolController {

    Random random = new Random();
    @GetMapping
    public String mostrarRol(@RequestParam("id") String id){
        return String.format("Rol con id: %s", id);
    }

    @GetMapping("/all")
    public List<Integer> mostrarRol(){
        List<Integer> aleatorio = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            aleatorio.add(random.nextInt());
        }
        return aleatorio;
    }

    @PostMapping
    public String ingresarRol(){
        return "Rol registrado correctamente";
    }

    @PutMapping
    public String actualizarRol(@RequestParam("id") String id){
        return String.format("El rol con id %s ha sido modificado",id);
    }

    @PatchMapping
    public String actualizarParametroRol(@RequestParam("id") String id){
        return "se ha actualizado el rol correctamente";
    }

    @DeleteMapping
    public String eliminarRol(@RequestParam("id") String id){
        return "se ha eliminado el rol correctamente";
    }
}
