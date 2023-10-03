package com.uco.yourplus.apiyourplus.controller.producto;

import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.facade.producto.SendDeleteMessageFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apiyourplus/productos")
public class EliminarProductoController {

    private final SendDeleteMessageFacade facade;

    public EliminarProductoController(SendDeleteMessageFacade facade) {
        this.facade = facade;
    }

    @DeleteMapping()
    public void execute(@RequestBody ProductoDTO productoDTO){
        facade.execute(productoDTO);
    }
}
