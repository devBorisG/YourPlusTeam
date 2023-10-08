package com.uco.yourplus.repositoryyourplus.config;

import com.uco.yourplus.crosscuttingyourplus.properties.PropertiesCatalogProductoProducer;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(PropertiesCatalogProductoProducer.class)
public class MessagingConfig {

    private PropertiesCatalogProductoProducer productoProducer;

    public MessagingConfig(@Qualifier("propertiesCatalogProductoProducer") PropertiesCatalogProductoProducer productoProducer) {
        this.productoProducer = productoProducer;
    }

    //Spring bean for producer save queue
    @Bean
    public Queue saveQueue(){
        return new Queue(productoProducer.getQueue().getSave());
    }

    //Spring bean for producer delete queue
    @Bean
    public Queue deleteQueue(){
        return new Queue(productoProducer.getQueue().getDelete());
    }

    //Spring bean for producer update queue
    @Bean
    public Queue updateQueue(){
        return new Queue(productoProducer.getQueue().getUpdate());
    }

    //Spring bean for producer list queue
    @Bean
    public Queue listQueue(){
        return new Queue(productoProducer.getQueue().getList());
    }

    //Spring bean for producer exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(productoProducer.getExchange());
    }

    //Binding between queue save an exchange using routing key
    @Bean
    public Binding saveBinding(Queue saveQueue, TopicExchange exchange){
        return BindingBuilder.bind(saveQueue)
                .to(exchange)
                .with(productoProducer.getRoutingkey().getSave());
    }

    //Binding between queue delete an exchange using routing key
    @Bean
    public Binding deleteBinding(Queue deleteQueue, TopicExchange exchange){
        return BindingBuilder.bind(deleteQueue)
                .to(exchange)
                .with(productoProducer.getRoutingkey().getDelete());
    }

    //Binding between queue update an exchange using routing key
    @Bean
    public Binding updateBinding(Queue updateQueue, TopicExchange exchange){
        return BindingBuilder.bind(updateQueue)
                .to(exchange)
                .with(productoProducer.getRoutingkey().getUpdate());
    }

    //Binding between queue list an exchange using routing key
    @Bean
    public Binding listBinding(Queue listQueue, TopicExchange exchange){
        return BindingBuilder.bind(listQueue)
                .to(exchange)
                .with(productoProducer.getRoutingkey().getList());
    }
}
