package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.laboratorio.implemtentation;

import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.laboratorio.RabbitMQUpdateProducerLaboratorio;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQUpdateProducerLaboratorioImpl implements RabbitMQUpdateProducerLaboratorio {


    @Override
    public void execute(LaboratorioDomain domain) {

    }
}
