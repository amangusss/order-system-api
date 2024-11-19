package alatoo.edu.kg.order_system.api.services.impl;

import alatoo.edu.kg.order_system.api.exceptions.OrderNotFoundException;
import alatoo.edu.kg.order_system.api.mapper.OrderMapper;
import alatoo.edu.kg.order_system.api.payload.OrderDTO;
import alatoo.edu.kg.order_system.api.rabbitmq.RabbitMQSender;
import alatoo.edu.kg.order_system.api.services.OrderService;
import alatoo.edu.kg.order_system.store.entity.Order;
import alatoo.edu.kg.order_system.store.repositories.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RabbitMQSender rabbitMQSender;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, RabbitMQSender rabbitMQSender) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.rabbitMQSender = rabbitMQSender;
    }

    @Transactional
    @Override
    public OrderDTO createOrder(OrderDTO orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order savedOrder = orderRepository.save(order);
        OrderDTO savedOrderDto = orderMapper.toDto(savedOrder);

        rabbitMQSender.sendOrder(savedOrderDto);

        return savedOrderDto;
    }


    @Transactional(readOnly = true)
    @Override
    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Transactional
    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OrderDTO updateOrder(Long id, OrderDTO orderDto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        existingOrder.setOverallPrice(orderDto.overallPrice());
        existingOrder.setOrderNumber(orderDto.orderNumber());

        Order updatedOrder = orderRepository.save(existingOrder);
        OrderDTO updatedOrderDto = orderMapper.toDto(updatedOrder);

        rabbitMQSender.sendOrder(updatedOrderDto);
        log.info ("The order has been updated and sent to RabbitMQ: {}", updatedOrderDto);

        return updatedOrderDto;
    }


    @Transactional
    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException(id);
        }
        orderRepository.deleteById(id);
        rabbitMQSender.sendNotification("Order with ID " + id + " has been deleted.");
        log.info ("Deletion notification sent to RabbitMQ: order ID {}", id);
    }



}
