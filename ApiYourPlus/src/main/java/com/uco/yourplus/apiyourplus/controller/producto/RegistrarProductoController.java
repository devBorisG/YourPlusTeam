package com.uco.yourplus.apiyourplus.controller.producto;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.facade.producto.SendSaveMessageFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.uco.yourplus.crosscuttingyourplus.helper.UUIDHelper.getNewUUID;

@RestController
@RequestMapping("/yourplus/v1/productos")
public class RegistrarProductoController {

    private final SendSaveMessageFacade facade;

    public RegistrarProductoController(SendSaveMessageFacade facade) {
        this.facade = facade;
    }

    @PostMapping()
    public ResponseEntity<Response<ProductoDTO>> execute(@RequestBody ProductoDTO productoDTO) {
        productoDTO.setId(getNewUUID());
        System.out.println(productoDTO.getId());
        System.out.println(productoDTO.getNombre());
        System.out.println(productoDTO.getPrecio());
        System.out.println(productoDTO.getDescripcion());
        System.out.println(productoDTO.getCategoria().getId());
        System.out.println(productoDTO.getLaboratorio().getId());
        System.out.println(productoDTO.getImagen());

        final Response<ProductoDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            facade.execute(productoDTO);
            response.addSuccesMessage("Producto Creado c");
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
