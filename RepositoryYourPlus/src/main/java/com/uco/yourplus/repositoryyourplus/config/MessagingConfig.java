package com.uco.yourplus.repositoryyourplus.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    //Exchange Definition
    @Value("${yourplus.management.producto.exchange}")
    private String exchange;

    //Queues Definitions
    @Value("${yourplus.management.producto.queue.save}")
    private String queueSave;

    @Value("${yourplus.management.producto.queue.delete}")
    private String queueDelete;

    @Value("${yourplus.management.producto.queue.update}")
    private String queueUpdate;

    @Value("${yourplus.management.producto.queue.list}")
    private String queueList;

    //Routing Keys Definitions
    @Value("${yourplus.management.producto.routingkey.save}")
    private String routingKeySave;

    @Value("${yourplus.management.producto.routingkey.delete}")
    private String routingKeyDelete;

    @Value("${yourplus.management.producto.routingkey.update}")
    private String routingKeyUpdate;

    @Value("${yourplus.management.producto.routingkey.list}")
    private String routingKeyList;

    //Spring bean for rabbit save queue
    @Bean
    public Queue saveQueue(){
        return new Queue(queueSave);
    }

    //Spring bean for rabbit delete queue
    @Bean
    public Queue deleteQueue(){
        return new Queue(queueDelete);
    }

    //Spring bean for rabbit update queue
    @Bean
    public Queue updateQueue(){
        return new Queue(queueUpdate);
    }

    //Spring bean for rabbit list queue
    @Bean
    public Queue listQueue(){
        return new Queue(queueList);
    }

    //Spring bean for rabbit exchange
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    //Binding between queue save an exchange using routing key
    @Bean
    public Binding saveBinding(Queue saveQueue, TopicExchange exchange){
        return BindingBuilder.bind(saveQueue)
                .to(exchange)
                .with(routingKeySave);
    }

    //Binding between queue delete an exchange using routing key
    @Bean
    public Binding deleteBinding(Queue deleteQueue, TopicExchange exchange){
        return BindingBuilder.bind(deleteQueue)
                .to(exchange)
                .with(routingKeyDelete);
    }

    //Binding between queue update an exchange using routing key
    @Bean
    public Binding updateBinding(Queue updateQueue, TopicExchange exchange){
        return BindingBuilder.bind(updateQueue)
                .to(exchange)
                .with(routingKeyUpdate);
    }

    //Binding between queue list an exchange using routing key
    @Bean
    public Binding listBinding(Queue listQueue, TopicExchange exchange){
        return BindingBuilder.bind(listQueue)
                .to(exchange)
                .with(routingKeyList);
    }
}
