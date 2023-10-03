package com.uco.yourplus.apiyourplus.controller.producto;

import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.facade.producto.SendMessageFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apiyourplus/productos")
public class RegistrarProductoController {

    private final SendMessageFacade facade;

    public RegistrarProductoController(SendMessageFacade facade) {
        this.facade = facade;
    }

    @PostMapping()
    public void execute(@RequestBody ProductoDTO productoDTO){
        facade.execute(productoDTO);
    }
}
