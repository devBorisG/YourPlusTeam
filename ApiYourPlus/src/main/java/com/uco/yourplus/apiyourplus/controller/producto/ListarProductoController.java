package com.uco.yourplus.apiyourplus.controller.producto;

import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.facade.producto.SendListMessageFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apiyourplus/productos")
public class ListarProductoController {

    private final SendListMessageFacade facade;

    public ListarProductoController(SendListMessageFacade facade) {
        this.facade = facade;
    }

    @GetMapping()
    public void execute(@RequestBody ProductoDTO productoDTO){
        facade.execute(productoDTO);
    }
}
