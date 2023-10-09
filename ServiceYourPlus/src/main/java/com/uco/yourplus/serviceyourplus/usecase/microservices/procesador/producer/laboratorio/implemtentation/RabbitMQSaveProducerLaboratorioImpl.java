package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.laboratorio.implemtentation;

import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.laboratorio.RabbitMQSaveProducerLaboratorio;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQSaveProducerLaboratorioImpl implements RabbitMQSaveProducerLaboratorio {


    @Override
    public void execute(LaboratorioDomain domain) {

    }
}
