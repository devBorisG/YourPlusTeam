package com.uco.yourplus.apiyourplus.controller.laboratorio;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;
import com.uco.yourplus.serviceyourplus.facade.laboratorio.SendDeleteLaboratoryMessageFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/yourplus/v1/laboratorios")
public class EliminarLaboratorioController {

    private final SendDeleteLaboratoryMessageFacade facade;

    public EliminarLaboratorioController(SendDeleteLaboratoryMessageFacade facade) {
        this.facade = facade;
    }

    @DeleteMapping()
    public ResponseEntity<Response<LaboratorioDTO>> execute(@RequestParam String id) {
        final Response<LaboratorioDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        LaboratorioDTO laboratorioDTO = new LaboratorioDTO();
        try {
            laboratorioDTO.setId(UUID.fromString(id));
            facade.execute(laboratorioDTO);
            response.addSuccesMessage("Se ha Eliminado el Laboratorio");
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
