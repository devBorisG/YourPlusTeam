package com.uco.yourplus.serviceyourplus.usecase.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.SendConsultLaboratoryMessage;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.laboratorio.RabbitMQConsultProducerLaboratorio;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.laboratorio.HandlerReceiveMessageListLaboratorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SendConsultLaboratoryMessageImpl implements SendConsultLaboratoryMessage {

    private final RabbitMQConsultProducerLaboratorio producerLaboratorio;
    private final HandlerReceiveMessageListLaboratorio receiverLaboratorio;

    public SendConsultLaboratoryMessageImpl(RabbitMQConsultProducerLaboratorio producerLaboratorio, HandlerReceiveMessageListLaboratorio receiverLaboratorio) {
        this.producerLaboratorio = producerLaboratorio;
        this.receiverLaboratorio = receiverLaboratorio;
    }

    @Override
    public List<LaboratorioDomain> execute(Optional<LaboratorioDomain> domain) {
        try{
            if(domain.isPresent()){
                producerLaboratorio.execute(domain.get());
                return receiverLaboratorio.waitForResponse(domain.get().toString());
            }
            return new ArrayList<>();
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado ejecuttando el caso de uso de enviar el laboratorio a la cola de consutlar");
        }
    }
}
