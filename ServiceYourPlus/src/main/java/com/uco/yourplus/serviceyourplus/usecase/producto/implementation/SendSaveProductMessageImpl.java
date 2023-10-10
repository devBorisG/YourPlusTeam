package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.producto.RabbitMQSaveProducerProducto;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.producto.HandlerReceiveMessageSaveProducto;
import com.uco.yourplus.serviceyourplus.usecase.producto.SendSaveProductMessage;
import org.springframework.stereotype.Service;

@Service
public class SendSaveProductMessageImpl implements SendSaveProductMessage {

    private final RabbitMQSaveProducerProducto producerProducto;

    private final HandlerReceiveMessageSaveProducto receiverProducto;

    public SendSaveProductMessageImpl(RabbitMQSaveProducerProducto producerProducto, HandlerReceiveMessageSaveProducto receiverProducto) {
        this.producerProducto = producerProducto;
        this.receiverProducto = receiverProducto;
    }

    @Override
    public void execute(ProductoDomain domain) {
        try {
            producerProducto.execute(domain);
            receiverProducto.waitForResponse(domain.getId().toString());
        } catch (ServiceCustomException exception) {
            throw exception;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado ejecutando el caso de uso de enviar el producto por la cola de registrar");
        }
    }
}
