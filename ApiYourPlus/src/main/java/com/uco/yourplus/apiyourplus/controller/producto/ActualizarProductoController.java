package com.uco.yourplus.apiyourplus.controller.producto;

import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.facade.producto.SendUpdateMessageFacade;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("apiyourplus/productos")
public class ActualizarProductoController {

    private final SendUpdateMessageFacade facade;

    public ActualizarProductoController(SendUpdateMessageFacade facade) {
        this.facade = facade;
    }

    @PutMapping()
    public void execute(@RequestBody ProductoDTO productoDTO){
        facade.execute(productoDTO);
    }
}
