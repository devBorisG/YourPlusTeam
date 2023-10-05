package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.producto.SendDeleteProductMessage;
import com.uco.yourplus.serviceyourplus.usecase.producer.producto.RabbitMQDeleteProducerProducto;
import org.springframework.stereotype.Service;

@Service
public class SendDeleteProductMessageImpl implements SendDeleteProductMessage {

    private final RabbitMQDeleteProducerProducto producerProducto;

    public SendDeleteProductMessageImpl(RabbitMQDeleteProducerProducto producerProducto) {
        this.producerProducto = producerProducto;
    }

    @Override
    public void execute(ProductoDomain domain) {
        try {
            producerProducto.execute(domain);
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado ejecutando el caso de uso de enviar el producto por la cola de eliminar");
        }
    }
}
