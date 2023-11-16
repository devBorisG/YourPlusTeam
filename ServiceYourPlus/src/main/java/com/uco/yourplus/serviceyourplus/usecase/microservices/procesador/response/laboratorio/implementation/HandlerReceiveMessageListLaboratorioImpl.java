package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.laboratorio.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.properties.PropertiesCatalogLaboratorioProducer;
import com.uco.yourplus.crosscuttingyourplus.properties.PropertiesCatalogLaboratorioReceiver;
import com.uco.yourplus.serviceyourplus.domain.LaboratorioDomain;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.laboratorio.HandlerReceiveMessageListLaboratorio;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.process.ProcessRabbitResponseLaboratorio;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.uco.yourplus.crosscuttingyourplus.helper.IntegerHelper.DEFAULT_TIMEOUT_MILLIS;

@Component
@EnableConfigurationProperties(PropertiesCatalogLaboratorioProducer.class)
public class HandlerReceiveMessageListLaboratorioImpl implements HandlerReceiveMessageListLaboratorio {

    private final RabbitTemplate rabbitTemplate;
    private final ProcessRabbitResponseLaboratorio processRabbitResponseLaboratorio;
    private final PropertiesCatalogLaboratorioReceiver laboratorioReceiver;

    public HandlerReceiveMessageListLaboratorioImpl(RabbitTemplate rabbitTemplate, ProcessRabbitResponseLaboratorio processRabbitResponseLaboratorio, PropertiesCatalogLaboratorioReceiver laboratorioReceiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.processRabbitResponseLaboratorio = processRabbitResponseLaboratorio;
        this.laboratorioReceiver = laboratorioReceiver;
    }

    @Override
    public List<LaboratorioDomain> waitForResponse(String requestId) {
        try {
            Object response = rabbitTemplate.receiveAndConvert(laboratorioReceiver.getQueue().getList(), DEFAULT_TIMEOUT_MILLIS);
            List<LaboratorioDomain> respuesta = processRabbitResponseLaboratorio.verifyContent(response, requestId);
            if (respuesta == null) {
                throw ServiceCustomException.createUserException("Algo salio mal, intenta nuevamente");
            }
            return respuesta;
        }catch (ServiceCustomException exception) {
            throw exception;
        } catch (AmqpException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error ejecutando la tarea AMQP");
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurro un error inesperado");
        }
    }
}
