package com.uco.yourplus.serviceyourplus.usecase.producer.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.crosscutting.CrosscuttingCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.properties.PropertiesCatalogProductoProducer;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.producer.configuration.producto.ConfigRabbitContentProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.producer.producto.RabbitMQUpdateProducerProducto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableConfigurationProperties(PropertiesCatalogProductoProducer.class)
public class RabbitMQUpdateProducerProductoImpl implements RabbitMQUpdateProducerProducto {

    private final PropertiesCatalogProductoProducer producer;
    private final RabbitTemplate rabbitTemplate;
    private final ConfigRabbitContentProductoDomain configRabbitProductContent;

    public RabbitMQUpdateProducerProductoImpl(@Qualifier("propertiesCatalogProductoProducer") PropertiesCatalogProductoProducer producer, RabbitTemplate rabbitTemplate, ConfigRabbitContentProductoDomain configRabbitProductContent) {
        this.producer = producer;
        this.rabbitTemplate = rabbitTemplate;
        this.configRabbitProductContent = configRabbitProductContent;
    }

    @Override
    public void execute(ProductoDomain domain) {
        try{
            MessageProperties messageProperties = configRabbitProductContent.generateMessageProperties(domain.getId());
            Optional<Message> bodyContent = configRabbitProductContent.getBodyMessage(domain, messageProperties);
            if (bodyContent.isEmpty()){
                throw ServiceCustomException.createTechnicalException("Ocurrió un error configurando las propiedades del Message para actualizar");
            }
            rabbitTemplate.convertAndSend(producer.getExchange(), producer.getRoutingkey().getUpdate(), bodyContent.get());
        }catch (CrosscuttingCustomException exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error en el ConfigRabbitContent para configurar el mensaje");
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error inesperado intentando realizar las configuraciones del mensaje");
        }
    }
}
