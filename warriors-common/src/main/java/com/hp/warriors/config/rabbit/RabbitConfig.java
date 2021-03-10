package com.hp.warriors.config.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
public class RabbitConfig {

//    /**
//     * 【 ====================== 审核系统推送房评 start ====================== 】
//     */
//    @Primary
//    @Bean(name = "connectionFactory")
//    public ConnectionFactory connectionFactory(@Value("${spring.rabbitmq.host}") String host,
//                                               @Value("${spring.rabbitmq.port}") int port,
//                                               @Value("${spring.rabbitmq.username}") String username,
//                                               @Value("${spring.rabbitmq.password}") String password) {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost(host);
//        connectionFactory.setPort(port);
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        connectionFactory.setPublisherConfirms(true);
//        return connectionFactory;
//    }
//
//    @Bean(name = "rabbitAdmin")
//    public RabbitAdmin rabbitAdmin(@Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
//        return new RabbitAdmin(connectionFactory);
//    }
//
//    @Bean(name = "listenerContainerFactory")
//    public SimpleRabbitListenerContainerFactory phpListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,
//                                                                            @Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        configurer.configure(factory, connectionFactory);
//        return factory;
//    }
//
//    /**
//     * 队列 eg : t.cqss.php_house_evaluation
//     */
//    @Bean(name = "queue")
//    public Queue phpQueue() {
//        return new Queue("house_evaluation");
//    }
//
//    /**
//     * 交换机 eg : EX_test.exchange
//     */
//    @Bean(name = "exchange")
//    public TopicExchange phpExchange() {
//        String topic = "EX_test.exchange";
//        return new TopicExchange(topic);
//    }
//
//    @Bean
//    Binding phpBinding(@Qualifier("queue") Queue queueMessages,
//                       @Qualifier("exchange") TopicExchange exchange,
//                       @Qualifier("rabbitAdmin") RabbitAdmin rabbitAdmin) {
//        Binding binding = BindingBuilder.bind(queueMessages).to(exchange).with("audit.house_info.online.update");
//        rabbitAdmin.declareBinding(binding);
//        return binding;
//    }

    @Bean(name = "newMQRentbackConnection")
    public RabbitTemplate newMQRentbackConnection() {
        try {
            CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
            connectionFactory.setAddresses("127.0.0.1");
            connectionFactory.setPort(5672);
            connectionFactory.setUsername("guest");
            connectionFactory.setPassword("guest");
            RabbitTemplate template = new RabbitTemplate(connectionFactory);
            template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
                @Override
                public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                    if (!ack) {
                        log.info("send message failed: " + cause); //+ correlationData.toString());
                        throw new RuntimeException("send error " + cause);
                    }
                    log.info("send message success: " + cause); //+ correlationData.toString());
                }
            });
            return template;
        } catch (Exception e) {
            log.error("连接不上待办的MQ,将影响待办的功能！请检查配置是否正确！");
        }
        return null;
    }
}
