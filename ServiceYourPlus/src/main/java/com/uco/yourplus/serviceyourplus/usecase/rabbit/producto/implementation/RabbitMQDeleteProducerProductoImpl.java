package com.uco.yourplus.serviceyourplus.usecase.rabbit.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.crosscutting.CrosscuttingCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.rabbit.configuration.producto.ConfigRabbitContentProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.rabbit.producto.RabbitMQDeleteProducerProducto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RabbitMQDeleteProducerProductoImpl implements RabbitMQDeleteProducerProducto {

    @Value("${yourplus.management.producto.exchange}")
    private String exchange;

    @Value("${yourplus.management.producto.routingkey.delete}")
    private String routingKeyDelete;

    private final RabbitTemplate rabbitTemplate;
    private final ConfigRabbitContentProductoDomain configRabbitProductContent;

    public RabbitMQDeleteProducerProductoImpl(RabbitTemplate rabbitTemplate, ConfigRabbitContentProductoDomain configRabbitProductContent) {
        this.rabbitTemplate = rabbitTemplate;
        this.configRabbitProductContent = configRabbitProductContent;
    }

    @Override
    public void execute(ProductoDomain domain) {
        try {
            MessageProperties messageProperties = configRabbitProductContent.generateMessageProperties(domain.getId());
            Optional<Message> bodyMessage = configRabbitProductContent.getBodyMessage(domain,messageProperties);
            if(bodyMessage.isEmpty()){
                throw ServiceCustomException.createTechnicalException("Ocurrió un error configurando las propiedades del Message para eliminar");
            }
            rabbitTemplate.convertAndSend(exchange, routingKeyDelete, bodyMessage.get());
        }catch (CrosscuttingCustomException exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error en el ConfigRabbitContent para configurar el mensaje");
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error inesperado intentando realizar las configuraciones del mensaje");
        }
    }
}
