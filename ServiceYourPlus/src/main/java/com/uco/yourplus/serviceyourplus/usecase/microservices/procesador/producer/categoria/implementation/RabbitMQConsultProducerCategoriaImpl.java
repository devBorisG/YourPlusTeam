package com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.categoria.implementation;

import com.uco.yourplus.crosscuttingyourplus.properties.PropertiesCatalogCategoriaProducer;
import com.uco.yourplus.serviceyourplus.domain.CategoriaDomain;
import com.uco.yourplus.serviceyourplus.usecase.microservices.procesador.producer.categoria.RabbitMQConsultProducerCategoria;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(PropertiesCatalogCategoriaProducer.class)
public class RabbitMQConsultProducerCategoriaImpl implements RabbitMQConsultProducerCategoria {

    private final PropertiesCatalogCategoriaProducer producer;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void execute(CategoriaDomain domain) {

    }
}
