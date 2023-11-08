package com.uco.yourplus.apiyourplus.controller.producto;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.facade.producto.SendDeleteMessageFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yourplus/v1/productos")
public class EliminarProductoController {

    private final SendDeleteMessageFacade facade;

    public EliminarProductoController(SendDeleteMessageFacade facade) {
        this.facade = facade;
    }

    @DeleteMapping()
    public ResponseEntity<Response<ProductoDTO>> execute(@RequestBody ProductoDTO productoDTO) {
        final Response<ProductoDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            facade.execute(productoDTO);
            response.addSuccesMessage("Producto eliminado");
        } catch (final YourPlusCustomException exception) {
            httpStatus = HttpStatus.BAD_REQUEST;
            if (exception.isTechnicalException()) {
                response.addErrorMessage("Ocurrio un error tecnico, intente nuevamente");
            } else {
                response.addErrorMessage(exception.getMessage());
            }
        } catch (final Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error del servidor, intente nuevamente");
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}
