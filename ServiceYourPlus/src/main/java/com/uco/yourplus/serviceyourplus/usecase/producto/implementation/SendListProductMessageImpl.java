package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.dtoyourplus.builder.CategoriaDTO;
import com.uco.yourplus.dtoyourplus.builder.LaboratorioDTO;
import com.uco.yourplus.dtoyourplus.builder.ProductoDTO;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.producto.RabbitMQListProducerProducto;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.producto.HandlerReceiveMessageListProducto;
import com.uco.yourplus.serviceyourplus.usecase.producto.SendListProductMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SendListProductMessageImpl implements SendListProductMessage {

    private final RabbitMQListProducerProducto producerProducto;
    private final HandlerReceiveMessageListProducto receiverProducto;
    public SendListProductMessageImpl(RabbitMQListProducerProducto producerProducto, HandlerReceiveMessageListProducto receiverProducto) {
        this.producerProducto = producerProducto;
        this.receiverProducto = receiverProducto;
    }

    @Override
    public List<ProductoDomain> execute(Optional<ProductoDomain> domain) {
        try {
            if (domain.isPresent()){
                producerProducto.execute(domain.get());
                return receiverProducto.waitForResponse(domain.get().getId().toString());
            }
            return new ArrayList<>();
        } catch (ServiceCustomException exception) {
            throw exception;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado ejecutando el caso de uso de enviar el producto por la cola de listar");
        }
    }
}
