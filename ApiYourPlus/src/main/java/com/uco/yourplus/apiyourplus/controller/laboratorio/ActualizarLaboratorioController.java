package com.uco.yourplus.apiyourplus.controller.laboratorio;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;
import com.uco.yourplus.serviceyourplus.facade.laboratorio.SendUpdateLaboratoryMessageFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/yourplus/v1/laboratorios")
public class ActualizarLaboratorioController {

    private final SendUpdateLaboratoryMessageFacade facade;

    public ActualizarLaboratorioController(SendUpdateLaboratoryMessageFacade facade) {
        this.facade = facade;
    }

    @PutMapping()
    public ResponseEntity<Response<LaboratorioDTO>> execute(@RequestBody LaboratorioDTO laboratorioDTO) {
        final Response<LaboratorioDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            facade.execute(laboratorioDTO);
            response.addSuccesMessage("Laboratorio actualizado");
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
