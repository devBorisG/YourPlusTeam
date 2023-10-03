package com.uco.yourplus.serviceyourplus.usecase.rabbit.implementation;

import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.rabbit.RabbitMQSaveProducerProducto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

import java.util.Optional;

@Service
public class RabbitMQSaveProducerProductoImpl implements RabbitMQSaveProducerProducto {

    @Value("${yourplus.management.producto.exchange}")
    private String exchange;

    @Value("${yourplus.management.producto.routingkey}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;
    private final ConfigRabbitContent configRabbitContent;

    public RabbitMQSaveProducerProductoImpl(RabbitTemplate rabbitTemplate, ConfigRabbitContent configRabbitContent) {
        this.rabbitTemplate = rabbitTemplate;
        this.configRabbitContent = configRabbitContent;
    }

    @Override
    public void execute(ProductoDomain domain) {
        MessageProperties messageProperties = configRabbitContent.generateMessageProperties(domain.getId());

        Optional<Message> bodyMessage = configRabbitContent.getBodyMessage(domain, messageProperties);
        if(bodyMessage.isEmpty()){
            return;
        }
        rabbitTemplate.convertAndSend(exchange,routingKey,bodyMessage);
    }
}
