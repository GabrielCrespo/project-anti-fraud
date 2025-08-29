package br.com.gcs.ms_antifraud_service.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${app.rabbit.exchange.order}")
    private String ordersExchangeName;

    @Value("${app.rabbit.queues.orderCreated}")
    private String orderCreatedQueueName;

    @Value("${app.rabbit.queues.fraudResult}")
    private String fraudResultQueueName;

    @Value("${app.rabbit.routing-keys.orderCreated}")
    private String orderCreatedRoutingKey;

    @Value("${app.rabbit.routing-keys.fraudChecked}")
    private String fraudCheckedRoutingKey;


    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(ordersExchangeName);
    }

    @Bean
    public Queue orderCreatedQueue() {
        return new Queue(orderCreatedQueueName, true);
    }

    @Bean
    public Queue fraudResultQueue() {
        return new Queue(fraudResultQueueName, true);
    }

    @Bean
    public Binding orderCreatedBinding() {
        return BindingBuilder.bind(orderCreatedQueue()).to(orderExchange()).with(orderCreatedRoutingKey);
    }

    @Bean
    public Binding fraudCheckBinding() {
        return BindingBuilder.bind(fraudResultQueue()).to(orderExchange()).with(fraudCheckedRoutingKey);
    }


}
