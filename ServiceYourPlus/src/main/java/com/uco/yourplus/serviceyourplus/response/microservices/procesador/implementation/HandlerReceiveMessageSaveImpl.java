package com.uco.yourplus.serviceyourplus.response.microservices.procesador.implementation;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.IntegerHelper;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import com.uco.yourplus.serviceyourplus.domain.ResponseDomain;
import com.uco.yourplus.serviceyourplus.domain.enumeration.StateResponse;
import com.uco.yourplus.serviceyourplus.response.microservices.procesador.HandlerReceiveMessageSave;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class HandlerReceiveMessageSaveImpl implements HandlerReceiveMessageSave {

    private final RabbitTemplate rabbitTemplate;

    private final MapperJsonObject mapperJsonObject;

    public HandlerReceiveMessageSaveImpl(RabbitTemplate rabbitTemplate, MapperJsonObject mapperJsonObject) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapperJsonObject = mapperJsonObject;
    }

    @Override
    public boolean waitForResponse(String requestId) {
        try{
            Object response = rabbitTemplate.receiveAndConvert("producto.queue.response.save", IntegerHelper.DEFAULT_TIMEOUT_MILLIS);
            if (response != null ){
                ResponseDomain<ProductoDomain> responseDomain = convertObject(response);
                if(Objects.equals(responseDomain.getId().toString(), requestId)){
                    if (responseDomain.getStateResponse() == StateResponse.ERROR){
                        throw ServiceCustomException.createUserException(responseDomain.getMessage());
                    }
                    return true;
                }
            }
            return false;
        }catch (ServiceCustomException exception){
            throw exception;
        } catch (AmqpException exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error ejecutando la tarea AMQP");
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurro un error inesperado");
        }
    }

    private ResponseDomain<ProductoDomain> convertObject(Object response){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Optional<String> base64 = mapperJsonObject.execute(response);
            String base64Correct = base64.get().replace("\"", "");
            byte[] originalBytes = Base64.getDecoder().decode(base64Correct);
            return objectMapper.readValue(originalBytes, ResponseDomain.class);
        } catch (JsonMappingException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error mappeando el json");
        } catch (IOException exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error procesando el json");
        }
    }
}
