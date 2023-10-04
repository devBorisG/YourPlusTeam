package com.uco.yourplus.serviceyourplus.usecase.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.laboratorio.SendSaveLaboratoryMessage;
import com.uco.yourplus.serviceyourplus.usecase.rabbit.laboratorio.RabbitMQSaveProducerLaboratorio;

public class SendSaveLaboratoryMessageImpl implements SendSaveLaboratoryMessage {

    private final RabbitMQSaveProducerLaboratorio producerLaboratorio;

    public SendSaveLaboratoryMessageImpl(RabbitMQSaveProducerLaboratorio producerLaboratorio) {
        this.producerLaboratorio = producerLaboratorio;
    }

    @Override
    public void execute(LaboratorioDomain domain) {
        try{
            producerLaboratorio.execute(domain);
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado ejecutando el caso de uso de enviar el laboratorio por la cola de registrar");
        }
    }
}
