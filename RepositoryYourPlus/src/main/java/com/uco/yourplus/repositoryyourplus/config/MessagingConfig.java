package com.uco.yourplus.repositoryyourplus.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class MessagingConfig {

    @Value("${yourplus.management.producto.queue.save}")
    private String queueSave;

    @Value("${yourplus.management.producto.exchange}")
    private String exchange;

    @Value("${yourplus.management.producto.routingkey}")
    private String routingKey;

    //Spring bean for rabbit save queue
    @Bean
    public Queue saveQueue(){
        return new Queue(queueSave);
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
                .with(routingKey);
    }

    //public AmqpTemplate template(ConnectionFactory connectionFactory){
    //    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    //    rabbitTemplate.setMessageConverter(converter());
    //    return rabbitTemplate;
    //}
}
