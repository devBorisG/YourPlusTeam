package com.uco.yourplus.serviceyourplus.facade.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.facade.laboratorio.SendConsultLaboratoryMessageFacade;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.SendConsultLaboratoryMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SendConsultLaboratoryMessageFacadeImpl implements SendConsultLaboratoryMessageFacade {

    private final SendConsultLaboratoryMessage useCase;

    public SendConsultLaboratoryMessageFacadeImpl(SendConsultLaboratoryMessage useCase){
        this.useCase = useCase;
    }

    @Override
    public List<LaboratorioDTO> execute(Optional<LaboratorioDTO> dto) {
        LaboratorioDomain laboratorioDomain = new LaboratorioDomain();
        List<LaboratorioDTO> convertResult = new ArrayList<>();
        try{
            BeanUtils.copyProperties(dto.get(), laboratorioDomain);
            List<LaboratorioDomain> laboratorioDomainList = useCase.execute(Optional.of(laboratorioDomain));
            LaboratorioDTO laboratorioDTO = new LaboratorioDTO();
            laboratorioDomainList.forEach(value -> {
                BeanUtils.copyProperties(value,laboratorioDTO);
                convertResult.add(laboratorioDTO);
            });
            return convertResult;
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error ejecutando el caso de uso de enviar mensaje para consultar los laboratorios");
        }
    }
}
