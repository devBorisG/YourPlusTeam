package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.categoria.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.crosscutting.CrosscuttingCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.properties.PropertiesCatalogCategoriaProducer;
import com.uco.yourplus.serviceyourplus.domain.CategoriaDomain;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.categoria.RabbitMQConsultProducerCategoria;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.configuration.categoria.ConfigRabbitContentCategoria;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableConfigurationProperties(PropertiesCatalogCategoriaProducer.class)
public class RabbitMQConsultProducerCategoriaImpl implements RabbitMQConsultProducerCategoria {

    private final PropertiesCatalogCategoriaProducer producer;
    private final RabbitTemplate rabbitTemplate;
    private final ConfigRabbitContentCategoria configRabbitContentCategoria;

    public RabbitMQConsultProducerCategoriaImpl(@Qualifier("propertiesCatalogCategoriaProducer") PropertiesCatalogCategoriaProducer producer,
                                                RabbitTemplate rabbitTemplate, ConfigRabbitContentCategoria configRabbitContentCategoria) {
        this.producer = producer;
        this.rabbitTemplate = rabbitTemplate;
        this.configRabbitContentCategoria = configRabbitContentCategoria;
    }

    @Override
    public void execute(CategoriaDomain domain) {
        try {
            MessageProperties messageProperties = configRabbitContentCategoria.generateMessageProperties(domain.getId());
            Optional<Message> bodyMessage = configRabbitContentCategoria.getBodyMessage(domain, messageProperties);
            if (bodyMessage.isEmpty()) {
                throw ServiceCustomException.createTechnicalException("No se pudo configurar las propiedades para el mensaje");
            }
            rabbitTemplate.convertAndSend(producer.getExchange(), producer.getRoutingKey().getList(), bodyMessage.get());
        } catch (CrosscuttingCustomException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error en el ConfigRabbitContent para configurar le mensaje");
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado intentando realizar las configuraciones del mensaje"+exception.getMessage());
        }
    }
}
