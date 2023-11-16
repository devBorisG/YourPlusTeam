package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.categoria.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.properties.PropertiesCatalogCategoriaProducer;
import com.uco.yourplus.crosscuttingyourplus.properties.PropertiesCatalogCategoriaReceiver;
import com.uco.yourplus.serviceyourplus.domain.CategoriaDomain;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.categoria.HandlerReceiveMessageListCategoria;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.process.ProcessRabbitResponseCategoria;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.uco.yourplus.crosscuttingyourplus.helper.IntegerHelper.DEFAULT_TIMEOUT_MILLIS;

@Component
@EnableConfigurationProperties(PropertiesCatalogCategoriaProducer.class)
public class HandlerReceiveMessageListCategoriaImpl implements HandlerReceiveMessageListCategoria {

    private final RabbitTemplate rabbitTemplate;
    private final ProcessRabbitResponseCategoria processRabbitResponseCategoria;
    private final PropertiesCatalogCategoriaReceiver categoriaReceiver;

    public HandlerReceiveMessageListCategoriaImpl(RabbitTemplate rabbitTemplate, ProcessRabbitResponseCategoria processRabbitResponseCategoria, PropertiesCatalogCategoriaReceiver categoriaReceiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.processRabbitResponseCategoria = processRabbitResponseCategoria;
        this.categoriaReceiver = categoriaReceiver;
    }

    @Override
    public List<CategoriaDomain> waitForResponse(String requestId) {
        try{
            Object response = rabbitTemplate.receiveAndConvert(categoriaReceiver.getQueue().getList(),DEFAULT_TIMEOUT_MILLIS);
            List<CategoriaDomain> respuesta = processRabbitResponseCategoria.verifyContent(response,requestId);
            System.out.println(categoriaReceiver.getQueue().getList());
            System.out.println(response);
            if(respuesta == null){
                throw ServiceCustomException.createUserException("algo salio mal, intente nuevamente");
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
