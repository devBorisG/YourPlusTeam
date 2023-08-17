package com.uco.yourplus.apiyourplus.controller.laboratorio;

import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("apiyourplus/laboratorio")
public class LaboratorioController {

    @GetMapping()
    public String mostrarLaboratorio(@RequestParam("id") String id){
        return id;
    }

    @PostMapping
    public String ingresarLaboratorio(){
        return "Laboratorio registrado correctamente";
    }

    @PutMapping
    public String actualizarLaboratorio(@RequestParam("id") String id){
        return "Laboratorio actualizado correctamente";
    }

    @PatchMapping
    public String actualizarParametroLaboratorio(@RequestParam("id") String id){
        return "se ha actualizado el parametro correctamente";
    }

    @DeleteMapping
    public String eliminarLaboratorio(@RequestParam("id") String id){
        return "se ha eliminado el laboratorio correctamente";
    }

}
