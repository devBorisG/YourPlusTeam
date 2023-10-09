package com.uco.yourplus.apiyourplus.controller.producto;


import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.facade.producto.ConsultarProductoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("apiyourplus/producto")
public class ConsultarProductoController {

    @Autowired
    private ConsultarProductoFacade facade;


    @GetMapping()
    public ResponseEntity<Response<ProductoDTO>> execute(@RequestBody ProductoDTO productoDTO) {
        Optional<ProductoDTO> existDto = Optional.ofNullable(productoDTO);
        final Response<ProductoDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            List<ProductoDTO> data = facade.execute(existDto);
            response.addSuccesMessage("Lista de productos");
            response.setData(data);
        } catch (final YourPlusCustomException yourPlusCustomException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            if (yourPlusCustomException.isTechnicalException()) {
                response.addErrorMessage("Ocurrió un error consultando, intente nuevamente");
            } else {
                response.addErrorMessage(yourPlusCustomException.getMessage());
            }
        } catch (final Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrió un error interno en el servidor, intente nuevamente");
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}





