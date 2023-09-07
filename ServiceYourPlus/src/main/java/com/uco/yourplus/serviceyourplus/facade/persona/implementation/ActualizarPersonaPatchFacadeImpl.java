package com.uco.yourplus.serviceyourplus.facade.persona.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.facade.persona.ActualizarPersonaPatchFacade;
import com.uco.yourplus.serviceyourplus.usecase.persona.ActualizarPersonaPatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ActualizarPersonaPatchFacadeImpl implements ActualizarPersonaPatchFacade {

    @Autowired
    private ActualizarPersonaPatch useCase;

    @Override
    public void execute(Map<String, Object> dto) {
        try {
            useCase.execute(dto);
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error intentando ejecutar el caso de uso: "+ exception.getMessage());
        }
    }
}
