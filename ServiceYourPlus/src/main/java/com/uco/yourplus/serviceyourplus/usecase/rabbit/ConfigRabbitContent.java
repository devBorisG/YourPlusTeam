package com.uco.yourplus.serviceyourplus.usecase.rabbit;

import com.uco.yourplus.crosscuttingyourplus.exceptions.crosscutting.CrosscuttingCustomException;
import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.crosscuttingyourplus.helper.json.MapperJsonObject;
import com.uco.yourplus.serviceyourplus.domain.ProductoDomain;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ConfigRabbitContent {

    private final MapperJsonObject mapperJsonObject;

    public ConfigRabbitContent(MapperJsonObject mapperJsonObject) {
        this.mapperJsonObject = mapperJsonObject;
    }

    public Optional<Message> getBodyMessage(ProductoDomain domain, MessageProperties messageProperties) {
        try{
            Optional<String> textMessage = mapperJsonObject.executeGson(domain);
            return textMessage.map(msg -> MessageBuilder.withBody(msg.getBytes())
                    .andProperties(messageProperties)
                    .build());
        }catch (CrosscuttingCustomException exception){
            throw exception;
        }catch (Exception exception){
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrió un Error inesperado generando el cuerpo del mensaje");
        }
    }

    public MessageProperties generateMessageProperties(UUID id) {
        return MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setHeader("idMessage", id.toString())
                .build();
    }
}
