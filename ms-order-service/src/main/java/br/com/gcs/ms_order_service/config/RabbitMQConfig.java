package br.com.gcs.ms_order_service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConfig.class);

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
        return new TopicExchange(ordersExchangeName, true, false);
    }

    @Bean
    public Queue orderCreatedQueue() {
        return new Queue(orderCreatedQueueName, true);
    }

    @Bean
    public Queue fraudResultQueue() {
        return new Queue(orderCreatedQueueName, true);
    }

    @Bean
    public Binding bindingOrderCreated() {
        return BindingBuilder.bind(orderCreatedQueue())
                .to(orderExchange())
                .with(orderCreatedRoutingKey);
    }

    @Bean
    public Binding bindingfraudResult() {
        return BindingBuilder.bind(fraudResultQueue())
                .to(orderExchange())
                .with(fraudCheckedRoutingKey);
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                LOGGER.info("✅ Mensagem confirmada pelo broker: {}", correlationData);
            } else {
                LOGGER.error("❌ Falha no envio da mensagem: {}", cause);
            }
        });

        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
