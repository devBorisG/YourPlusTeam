package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.crosscutting.CrosscuttingCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.properties.PropertiesCatalogProductoProducer;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.configuration.producto.ConfigRabbitContentProduct;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.producto.RabbitMQDeleteProducerProducto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableConfigurationProperties(PropertiesCatalogProductoProducer.class)
public class RabbitMQDeleteProducerProductoImpl implements RabbitMQDeleteProducerProducto {

    private final PropertiesCatalogProductoProducer producer;
    private final RabbitTemplate rabbitTemplate;
    private final ConfigRabbitContentProduct configRabbitProductContent;

    public RabbitMQDeleteProducerProductoImpl(@Qualifier("propertiesCatalogProductoProducer") PropertiesCatalogProductoProducer producer, RabbitTemplate rabbitTemplate, ConfigRabbitContentProduct configRabbitProductContent) {
        this.producer = producer;
        this.rabbitTemplate = rabbitTemplate;
        this.configRabbitProductContent = configRabbitProductContent;
    }

    @Override
    public void execute(ProductoDomain domain) {
        try {
            MessageProperties messageProperties = configRabbitProductContent.generateMessageProperties(domain.getId());
            Optional<Message> bodyMessage = configRabbitProductContent.getBodyMessage(domain, messageProperties);
            if (bodyMessage.isEmpty()) {
                throw ServiceCustomException.createTechnicalException("Ocurrió un error configurando las propiedades del Message para eliminar");
            }
            rabbitTemplate.convertAndSend(producer.getExchange(), producer.getRoutingkey().getDelete(), bodyMessage.get());
        } catch (CrosscuttingCustomException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error en el ConfigRabbitContent para configurar el mensaje");
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un error inesperado intentando realizar las configuraciones del mensaje");
        }
    }
}
