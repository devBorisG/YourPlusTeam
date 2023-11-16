package com.uco.yourplus.apiyourplus.controller.categoria;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.CategoriaDTO;
import com.uco.yourplus.serviceyourplus.facade.categoria.SendListMessageFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/yourplus/v1/categorias")
public class ConsultarCategoriaController {

    private final SendListMessageFacade facade;

    public ConsultarCategoriaController(SendListMessageFacade facade) {
        this.facade = facade;
    }

    @GetMapping()
    public ResponseEntity<Response<CategoriaDTO>> execute(@RequestBody CategoriaDTO categoriaDTO){
        final Response<CategoriaDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try{
            List<CategoriaDTO> categoriaDTOList = facade.execute(Optional.of(categoriaDTO));
            response.addSuccesMessage("consulta de categorias exitoso");
            response.setData(categoriaDTOList);
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
