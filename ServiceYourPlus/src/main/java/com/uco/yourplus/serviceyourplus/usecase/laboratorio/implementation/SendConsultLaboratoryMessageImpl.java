package com.uco.yourplus.serviceyourplus.usecase.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.SendConsultLaboratoryMessage;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.laboratorio.RabbitMQConsultProducerLaboratorio;
import org.springframework.stereotype.Service;

@Service
public class SendConsultLaboratoryMessageImpl implements SendConsultLaboratoryMessage {

    private final RabbitMQConsultProducerLaboratorio producerLaboratorio;

    public SendConsultLaboratoryMessageImpl(RabbitMQConsultProducerLaboratorio producerLaboratorio) {
        this.producerLaboratorio = producerLaboratorio;
    }

    @Override
    public void execute(LaboratorioDomain domain) {
        try{
            producerLaboratorio.execute(domain);
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado ejecuttando el caso de uso de enviar el laboratorio a la cola de consutlar");
        }
    }
}
