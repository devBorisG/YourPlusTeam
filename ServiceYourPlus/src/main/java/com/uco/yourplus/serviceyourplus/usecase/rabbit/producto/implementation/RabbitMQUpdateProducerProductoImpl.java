package com.uco.yourplus.serviceyourplus.usecase.rabbit.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.crosscutting.CrosscuttingCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.rabbit.ConfigRabbitContent;
import com.uco.yourplus.serviceyourplus.usecase.rabbit.producto.RabbitMQUpdateProducerProducto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RabbitMQUpdateProducerProductoImpl implements RabbitMQUpdateProducerProducto {

    @Value("${yourplus.management.producto.exchange}")
    private String exchange;

    @Value("${yourplus.management.producto.routingkey.update}")
    private String routingKeyUpdate;

    private final RabbitTemplate rabbitTemplate;
    private final ConfigRabbitContent configRabbitContent;

    public RabbitMQUpdateProducerProductoImpl(RabbitTemplate rabbitTemplate, ConfigRabbitContent configRabbitContent) {
        this.rabbitTemplate = rabbitTemplate;
        this.configRabbitContent = configRabbitContent;
    }

    @Override
    public void execute(ProductoDomain domain) {
        try{
            MessageProperties messageProperties = configRabbitContent.generateMessageProperties(domain.getId());
            Optional<Message> bodyContent = configRabbitContent.getBodyMessage(domain, messageProperties);
            if (bodyContent.isEmpty()){
                throw ServiceCustomException.createTechnicalException("Ocurrió un error configurando las propiedades del Message");
            }
            rabbitTemplate.convertAndSend(exchange, routingKeyUpdate, bodyContent.get());
        }catch (CrosscuttingCustomException exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error en el ConfigRabbitContent para configurar el mensaje");
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error inesperado intentando realizar las configuraciones del mensaje");
        }
    }
}