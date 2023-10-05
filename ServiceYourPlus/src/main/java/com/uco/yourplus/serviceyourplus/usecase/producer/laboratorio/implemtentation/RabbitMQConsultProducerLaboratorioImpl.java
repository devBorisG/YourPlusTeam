package com.uco.yourplus.serviceyourplus.usecase.producer.laboratorio.implemtentation;

import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.producer.laboratorio.RabbitMQConsultProducerLaboratorio;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsultProducerLaboratorioImpl implements RabbitMQConsultProducerLaboratorio {

    @Override
    public void execute(LaboratorioDomain domain) {

    }
}
