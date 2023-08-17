package com.uco.yourplus.apiyourplus.controller.producto;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("apiyourplus/producto")
public class ProductoController {
    Random random = new Random();
    @GetMapping
    public String mostrarProducto(@RequestParam("id") String id){
        return String.format("Producto con id: %s", id);
    }

    @GetMapping("/all")
    public List<Integer> mostrarProductos(){
        List<Integer> aleatorio = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            aleatorio.add(random.nextInt());
        }
        return aleatorio;
    }

    @PostMapping
    public String ingresarProducto(){
        return "Producto registrado correctamente";
    }

    @PutMapping
    public String actualizarProducto(@RequestParam("id") String id){
        return String.format("El producto con id %s ha sido modificado",id);
    }

    @PatchMapping
    public String actualizarParametroProducto(@RequestParam("id") String id){
        return "se ha actualizado el parametro correctamente";
    }

    @DeleteMapping
    public String eliminarProducto(@RequestParam("id") String id){
        return "se ha eliminado el producto correctamente";
    }

}
