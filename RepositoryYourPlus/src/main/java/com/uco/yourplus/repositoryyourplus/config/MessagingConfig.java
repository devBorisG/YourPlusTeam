package com.uco.yourplus.repositoryyourplus.config;

import com.uco.yourplus.crosscuttingyourplus.properties.*;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({PropertiesCatalogProductoProducer.class,
        PropertiesCatalogProductoReceiver.class,
        PropertiesCatalogLaboratorioProducer.class,
        PropertiesCatalogLaboratorioProducer.class})
public class MessagingConfig {

    private final PropertiesCatalogProductoProducer productoProducer;
    private final PropertiesCatalogProductoReceiver productoReceiver;
    private final PropertiesCatalogCategoriaReceiver categoriaReceiver;
    private final PropertiesCatalogCategoriaProducer categoriaProducer;

    private final PropertiesCatalogLaboratorioProducer laboratorioProducer;

    private final PropertiesCatalogLaboratorioReceiver laboratorioReceiver;

    public MessagingConfig(@Qualifier("propertiesCatalogProductoProducer") PropertiesCatalogProductoProducer productoProducer, @Qualifier("propertiesCatalogProductoReceiver") PropertiesCatalogProductoReceiver productoReceiver,
                           @Qualifier("propertiesCatalogCategoriaReceiver") PropertiesCatalogCategoriaReceiver categoriaReceiver, @Qualifier("propertiesCatalogCategoriaProducer") PropertiesCatalogCategoriaProducer categoriaProducer, @Qualifier("propertiesCatalogLaboratorioProducer") PropertiesCatalogLaboratorioProducer laboratorioProducer, PropertiesCatalogLaboratorioReceiver laboratorioReceiver) {
        this.productoProducer = productoProducer;
        this.productoReceiver = productoReceiver;
        this.categoriaReceiver = categoriaReceiver;
        this.categoriaProducer = categoriaProducer;
        this.laboratorioProducer = laboratorioProducer;
        this.laboratorioReceiver = laboratorioReceiver;
    }

    //Spring bean for producer save queue
    @Bean
    public Queue listQueueCategoria(){
        return new Queue(categoriaProducer.getQueue().getList());
    }

    @Bean
    public Queue responseCategoriaList(){
        return new Queue(categoriaReceiver.getQueue().getList());
    }
    @Bean
    public Queue saveQueue() {
        return new Queue(productoProducer.getQueue().getSave());
    }

    //Spring bean for producer delete queue
    @Bean
    public Queue deleteQueue() {
        return new Queue(productoProducer.getQueue().getDelete());
    }

    //Spring bean for producer update queue
    @Bean
    public Queue updateQueue() {
        return new Queue(productoProducer.getQueue().getUpdate());
    }

    //Spring bean for producer list queue
    @Bean
    public Queue listQueue() {
        return new Queue(productoProducer.getQueue().getList());
    }

    @Bean
    public Queue responseSaveQueue() {
        return new Queue(productoReceiver.getQueue().getSave());
    }

    //Spring bean for producer exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(productoProducer.getExchange());
    }

    @Bean
    public TopicExchange exchangeResponseProcesador() {
        return new TopicExchange(productoReceiver.getExchange());
    }

    @Bean
    public TopicExchange exchangeCategoriaProducer(){
        return new TopicExchange(categoriaProducer.getExchange());
    }

    @Bean
    public TopicExchange exchangeCategoriaReceiver(){
        return new TopicExchange(categoriaReceiver.getExchange());
    }

    //Binding between queue save an exchange using routing key
    @Bean
    public Binding saveBinding(Queue saveQueue, TopicExchange exchange) {
        return BindingBuilder.bind(saveQueue)
                .to(exchange)
                .with(productoProducer.getRoutingkey().getSave());
    }

    //Binding between queue delete an exchange using routing key
    @Bean
    public Binding deleteBinding(Queue deleteQueue, TopicExchange exchange) {
        return BindingBuilder.bind(deleteQueue)
                .to(exchange)
                .with(productoProducer.getRoutingkey().getDelete());
    }

    //Binding between queue update an exchange using routing key
    @Bean
    public Binding updateBinding(Queue updateQueue, TopicExchange exchange) {
        return BindingBuilder.bind(updateQueue)
                .to(exchange)
                .with(productoProducer.getRoutingkey().getUpdate());
    }

    //Binding between queue list an exchange using routing key
    @Bean
    public Binding listBinding(Queue listQueue, TopicExchange exchange) {
        return BindingBuilder.bind(listQueue)
                .to(exchange)
                .with(productoProducer.getRoutingkey().getList());
    }

    @Bean
    public Binding responseSaveBinding(Queue saveQueue, TopicExchange exchange) {
        return BindingBuilder.bind(saveQueue)
                .to(exchange)
                .with(productoReceiver.getRoutingkey().getSave());
    }

    @Bean
    public Binding categoriaListBindingProducer(Queue listQueueCategoria,TopicExchange exchangeCategoriaProducer){
        return BindingBuilder.bind(listQueueCategoria)
                .to(exchangeCategoriaProducer)
                .with(categoriaProducer.getRoutingKey().getList());
    }

    @Bean
    public Binding categoriaListBindingReceiver(Queue responseCategoriaList,TopicExchange exchangeCategoriaReceiver){
        return BindingBuilder.bind(responseCategoriaList)
                .to(exchangeCategoriaReceiver)
                .with(categoriaReceiver.getRoutingKey().getList());
    }
}
