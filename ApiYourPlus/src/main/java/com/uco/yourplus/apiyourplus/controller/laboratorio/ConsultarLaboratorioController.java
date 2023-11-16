package com.uco.yourplus.apiyourplus.controller.laboratorio;

import com.uco.yourplus.apiyourplus.controller.response.Response;
import com.uco.yourplus.crosscuttingyourplus.exceptions.YourPlusCustomException;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;
import com.uco.yourplus.serviceyourplus.facade.laboratorio.SendConsultLaboratoryMessageFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/yourplus/v1/laboratorios")
public class ConsultarLaboratorioController {

    private final SendConsultLaboratoryMessageFacade facade;

    public ConsultarLaboratorioController(SendConsultLaboratoryMessageFacade facade) {
        this.facade = facade;
    }

    @GetMapping()
    public ResponseEntity<Response<LaboratorioDTO>> execute() {
        final Response<LaboratorioDTO> response = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
        try {
            List<LaboratorioDTO> laboratorioDTOList = facade.execute(Optional.of(new LaboratorioDTO()));
            response.addSuccesMessage("Consulta de Laboratorios exitosos");
            response.setData(laboratorioDTOList);
        } catch (final YourPlusCustomException exception) {
            httpStatus = HttpStatus.BAD_REQUEST;
            if (exception.isTechnicalException()) {
                response.addErrorMessage("Ocurrio un error tecnico, intente nuevamente"+exception.getMessage());
            } else {
                response.addErrorMessage(exception.getMessage());
            }
        } catch (final Exception exception) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            response.addFatalMessage("Ocurrio un error del servidor, intente nuevamente");
        }
        System.out.println("Laboratorios: "+response.getData().toString());
        return new ResponseEntity<>(response, httpStatus);
    }
}
