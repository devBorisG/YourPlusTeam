package com.uco.yourplus.apiyourplus.controller.producto;


import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.ProductosDTO;
import com.uco.yourplus.serviceyourplus.facade.producto.ConsultarProductosFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("apiyourplus/persona")
public class ConsultarProductosController {

    @Autowired

    private ConsultarProductosFacade facade;

    @GetMapping()
    public ResponseEntity<Response<ProductosDTO>> consultarProductos(@RequestBody ProductosDTO productoDTO){
        Optional<ProductosDTO> existDto = Optional.ofNullable(productoDTO);
        final Response<ProductosDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try{
            List<ProductosDTO> data = facade.execute(existDto);
            response.addSuccesMessage("Lista de productos");
            response.setData(data);
        }catch (final YourPlusCustomException yourPlusCustomException){
            httpStatus = HttpStatus.BAD_REQUEST;
            if(yourPlusCustomException.isTechnicalException()){
                response.addErrorMessage("Ocurrió un error consultando los productos, inténtelo nuevamente");
            }else {
                response.addErrorMessage(yourPlusCustomException.getMessage());
            }
        }catch (final Exception exception){
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrió un error interno en el servidor, inténtelo nuevamente");
        }
        return new ResponseEntity<>(response, httpStatus);
    }



}


