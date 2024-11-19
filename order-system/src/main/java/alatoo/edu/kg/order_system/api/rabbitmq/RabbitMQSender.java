package alatoo.edu.kg.order_system.api.rabbitmq;

import alatoo.edu.kg.order_system.api.payload.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQSender.class);
    private final RabbitTemplate rabbitTemplate;

    public RabbitMQSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendOrder(OrderDTO orderDto) {
        log.info("Попытка отправки сообщения в RabbitMQ: {}", orderDto);
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.ORDER_EXCHANGE,
                RabbitMQConfig.ORDER_ROUTING_KEY,
                orderDto
        );
        log.info("Сообщение отправлено в RabbitMQ.");
    }

    public void sendNotification(String message) {
        System.out.println("Отправка уведомления: " + message);
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_EXCHANGE, RabbitMQConfig.ORDER_ROUTING_KEY, message);
    }
}
