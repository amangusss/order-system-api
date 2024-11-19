package alatoo.edu.kg.user_system.api.rabbitmq;

import alatoo.edu.kg.user_system.api.payload.order.OrderDTO;
import alatoo.edu.kg.user_system.api.services.OrderProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiver {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQReceiver.class);
    private final OrderProcessor orderProcessor;

    public RabbitMQReceiver(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    @RabbitListener(queues = RabbitMQConfig.USER_QUEUE)
    public void receiveOrder(OrderDTO orderDto) {
        log.info("Получен заказ из RabbitMQ: {}", orderDto);
        orderProcessor.saveOrderTemporarily(orderDto);
    }
}

