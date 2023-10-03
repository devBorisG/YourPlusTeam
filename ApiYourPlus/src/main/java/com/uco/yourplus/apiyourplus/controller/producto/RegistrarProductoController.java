package com.uco.yourplus.apiyourplus.controller.producto;

import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.facade.producto.SendMessageFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("apiyourplus/productos")
public class RegistrarProductoController {

    private final SendMessageFacade facade;

    @Autowired
    public RegistrarProductoController(SendMessageFacade facade) {
        this.facade = facade;
    }

    @PostMapping()
    public void execute(@RequestBody ProductoDTO productoDTO){
        facade.execute(productoDTO);
    }
}
