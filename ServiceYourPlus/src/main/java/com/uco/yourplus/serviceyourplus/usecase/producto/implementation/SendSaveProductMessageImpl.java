package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.response.microservices.procesador.HandlerReceiveMessageSave;
import com.uco.yourplus.serviceyourplus.usecase.producto.SendSaveProductMessage;
import com.uco.yourplus.serviceyourplus.usecase.producer.producto.RabbitMQSaveProducerProducto;
import org.springframework.stereotype.Service;

@Service
public class SendSaveProductMessageImpl implements SendSaveProductMessage {

    private final RabbitMQSaveProducerProducto producerProducto;

    private final HandlerReceiveMessageSave receiverProducto;

    public SendSaveProductMessageImpl(RabbitMQSaveProducerProducto producerProducto, HandlerReceiveMessageSave receiverProducto) {
        this.producerProducto = producerProducto;
        this.receiverProducto = receiverProducto;
    }

    @Override
    public void execute(ProductoDomain domain) {
        try {
            producerProducto.execute(domain);
            if(!receiverProducto.waitForResponse(domain.getId().toString())){
                throw ServiceCustomException.createUserException("Algo salio mal, intentalo de nuevo");
            }
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado ejecutando el caso de uso de enviar el producto por la cola de registrar");
        }
    }
}
