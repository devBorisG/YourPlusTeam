package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.producto.SendUpdateProductMessage;
import com.uco.yourplus.serviceyourplus.usecase.rabbit.producto.RabbitMQUpdateProducerProducto;
import org.springframework.stereotype.Service;

@Service
public class SendUpdateProductMessageImpl implements SendUpdateProductMessage {

    private final RabbitMQUpdateProducerProducto producerProducto;

    public SendUpdateProductMessageImpl(RabbitMQUpdateProducerProducto producerProducto) {
        this.producerProducto = producerProducto;
    }

    @Override
    public void execute(ProductoDomain domain) {
        try {
            producerProducto.execute(domain);
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado ejecutando el caso de uso de enviar el producto por la cola de actualizar");
        }
    }
}
