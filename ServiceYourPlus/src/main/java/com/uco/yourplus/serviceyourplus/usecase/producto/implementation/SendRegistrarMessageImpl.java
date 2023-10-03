package com.uco.yourplus.serviceyourplus.usecase.producto.implementation;

import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.producto.SendRegistrarMessage;
import com.uco.yourplus.serviceyourplus.usecase.rabbit.producto.RabbitMQSaveProducerProducto;
import org.springframework.stereotype.Service;

@Service
public class SendRegistrarMessageImpl implements SendRegistrarMessage {

    private final RabbitMQSaveProducerProducto producerProducto;

    public SendRegistrarMessageImpl(RabbitMQSaveProducerProducto producerProducto) {
        this.producerProducto = producerProducto;
    }

    @Override
    public void execute(ProductoDomain domain) {
        producerProducto.execute(domain);
    }
}
