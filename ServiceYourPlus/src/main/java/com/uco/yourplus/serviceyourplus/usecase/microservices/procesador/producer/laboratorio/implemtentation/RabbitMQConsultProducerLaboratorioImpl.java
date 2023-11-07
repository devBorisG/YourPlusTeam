package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.laboratorio.implemtentation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.crosscutting.CrosscuttingCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.properties.PropertiesCatalogLaboratorioProducer;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.configuration.laboratorio.ConfigRabbitContentLaboratory;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.laboratorio.RabbitMQConsultProducerLaboratorio;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableConfigurationProperties(PropertiesCatalogLaboratorioProducer.class)
public class RabbitMQConsultProducerLaboratorioImpl implements RabbitMQConsultProducerLaboratorio {

    private final PropertiesCatalogLaboratorioProducer producer;
    private final RabbitTemplate rabbitTemplate;
    private final ConfigRabbitContentLaboratory configRabbitContentLaboratory;

    public RabbitMQConsultProducerLaboratorioImpl(@Qualifier("propertiesCatalogLaboratorioProducer") PropertiesCatalogLaboratorioProducer producer, RabbitTemplate rabbitTemplate, ConfigRabbitContentLaboratory configRabbitContentLaboratory) {
        this.producer = producer;
        this.rabbitTemplate = rabbitTemplate;
        this.configRabbitContentLaboratory = configRabbitContentLaboratory;
    }

    @Override
    public void execute(LaboratorioDomain domain) {
        try{
            MessageProperties messageProperties = configRabbitContentLaboratory.generateMessageProperties(domain.getId());
            Optional<Message> bodyMessage = configRabbitContentLaboratory.getBodyMessage(domain, messageProperties);
            if (bodyMessage.isEmpty()){
                throw ServiceCustomException.createTechnicalException("No se pudo configurar las propiedades del mensaje para Listar");
            }
            rabbitTemplate.convertAndSend(producer.getExchange(), producer.getRoutingKey().getList(), bodyMessage.get());
        }catch (CrosscuttingCustomException exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error en el ConfigRabbitContent para configurar le mensaje");
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado intentando realizar las configuraciones del mensaje");
        }
    }
}
