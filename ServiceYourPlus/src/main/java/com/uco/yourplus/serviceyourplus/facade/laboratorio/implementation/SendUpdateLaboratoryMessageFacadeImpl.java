package com.uco.yourplus.serviceyourplus.facade.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.facade.laboratorio.SendUpdateLaboratoryMessageFacade;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.SendUpdateLaboratoryMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SendUpdateLaboratoryMessageFacadeImpl implements SendUpdateLaboratoryMessageFacade {

    private final SendUpdateLaboratoryMessage  useCase;

    public SendUpdateLaboratoryMessageFacadeImpl(SendUpdateLaboratoryMessage useCase) {
        this.useCase = useCase;
    }

    @Override
    public void execute(LaboratorioDTO dto) {
        LaboratorioDomain laboratorioDomain = new LaboratorioDomain();
        try {
            BeanUtils.copyProperties(dto, laboratorioDomain);
            useCase.execute(laboratorioDomain);
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception,"Ocurrió un error ejecutando el caso de uso de enviar mensaje para actualizar el laboratorio");
        }
    }
}
