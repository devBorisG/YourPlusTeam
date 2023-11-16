package com.uco.yourplus.serviceyourplus.usecase.categoria.implementation;

import com.uco.yourplus.crosscuttingyourplus.exceptions.service.ServiceCustomException;
import com.uco.yourplus.serviceyourplus.domain.CategoriaDomain;
import com.uco.yourplus.serviceyourplus.usecase.categoria.SendListCategoriaMessage;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.categoria.RabbitMQConsultProducerCategoria;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.response.categoria.HandlerReceiveMessageListCategoria;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SendListCategoriaMessageImpl implements SendListCategoriaMessage {

    private final RabbitMQConsultProducerCategoria producerCategoria;
    private final HandlerReceiveMessageListCategoria receiverCategoria;

    public SendListCategoriaMessageImpl(RabbitMQConsultProducerCategoria producerCategoria, HandlerReceiveMessageListCategoria receiverCategoria) {
        this.producerCategoria = producerCategoria;
        this.receiverCategoria = receiverCategoria;
    }

    @Override
    public List<CategoriaDomain> execute(Optional<CategoriaDomain> domain) {
        try{
            if(domain.isPresent()){
                producerCategoria.execute(domain.get());
                return receiverCategoria.waitForResponse(domain.get().getId().toString());
            }
            return new ArrayList<>();
        }catch (ServiceCustomException exception) {
            throw exception;
        } catch (Exception exception) {
            throw ServiceCustomException.createTechnicalException(exception, "Ocurrio un error inesperado ejecutando el caso de uso de enviar la categoria por la cola de listar");
        }
    }
}
