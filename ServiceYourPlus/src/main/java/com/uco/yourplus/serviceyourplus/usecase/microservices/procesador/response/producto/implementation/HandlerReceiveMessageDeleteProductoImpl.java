package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.producto.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.properties.PropertiesCatalogProductoReceiver;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.ProcessRabbitResponse;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.producto.HandlerReceiveMessageDeleteProducto;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;


import static com.uco.yourplus.crosscuttingyourplus.helper.IntegerHelper.DEFAULT_TIMEOUT_MILLIS;

@Component
@EnableConfigurationProperties(PropertiesCatalogProductoReceiver.class)
public class HandlerReceiveMessageDeleteProductoImpl implements HandlerReceiveMessageDeleteProducto {

    private final RabbitTemplate rabbitTemplate;
    private final ProcessRabbitResponse<ProductoDomain> processRabbitResponse;
    private final PropertiesCatalogProductoReceiver productoReceiver;

    public HandlerReceiveMessageDeleteProductoImpl(RabbitTemplate rabbitTemplate, ProcessRabbitResponse<ProductoDomain> convertObject, @Qualifier("propertiesCatalogProductoReceiver") PropertiesCatalogProductoReceiver productoReceiver) {
        this.rabbitTemplate = rabbitTemplate;
        this.processRabbitResponse = convertObject;
        this.productoReceiver = productoReceiver;
    }

    @Override
    public void waitForResponse(String requestId) {
        try{
            Object response = rabbitTemplate.receiveAndConvert(productoReceiver.getQueue().getDelete(), DEFAULT_TIMEOUT_MILLIS);
            if(processRabbitResponse.verifyContent(response,requestId)==null){
                throw ServiceCustomException.createUserException("Algo salio mal, intenta nuevamente");
            }
        }catch (ServiceCustomException exception){
            throw exception;
        }catch (AmqpException exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error ejecutando la tarea AMQP");
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurro un error inesperado");
        }
    }
}
