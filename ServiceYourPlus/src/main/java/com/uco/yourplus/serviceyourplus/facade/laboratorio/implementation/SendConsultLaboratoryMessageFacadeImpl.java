package com.uco.yourplus.serviceyourplus.facade.laboratorio.implementation;

import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;
import com.uco.yourplus.serviceyourplus.facade.laboratorio.SendConsultLaboratoryMessageFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SendConsultLaboratoryMessageFacadeImpl implements SendConsultLaboratoryMessageFacade {


    @Override
    public void execute(LaboratorioDTO dto) {

    }
}
