package com.uco.yourplus.serviceyourplus.usecase.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.SendUpdateLaboratoryMessage;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.laboratorio.RabbitMQUpdateProducerLaboratorio;
import org.springframework.stereotype.Service;

@Service
public class SendUpdateLaboratoryMessageImpl implements SendUpdateLaboratoryMessage {

    private final RabbitMQUpdateProducerLaboratorio producerLaboratorio;

    public SendUpdateLaboratoryMessageImpl(RabbitMQUpdateProducerLaboratorio producerLaboratorio) {
        this.producerLaboratorio = producerLaboratorio;
    }

    @Override
    public void execute(LaboratorioDomain domain) {
        try {
            producerLaboratorio.execute(domain);
        } catch (ServiceCustomException exception) {
            throw exception;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado ejecutando el caso de uso de enviar el producto por la cola de actualizar");
        }
    }
}
