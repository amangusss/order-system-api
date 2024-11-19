package alatoo.edu.kg.order_system.api.services.impl;

import alatoo.edu.kg.order_system.api.exceptions.OrderItemNotFoundException;
import alatoo.edu.kg.order_system.api.exceptions.OrderNotFoundException;
import alatoo.edu.kg.order_system.api.mapper.OrderItemMapper;
import alatoo.edu.kg.order_system.api.payload.OrderItemDTO;
import alatoo.edu.kg.order_system.api.services.OrderItemService;
import alatoo.edu.kg.order_system.store.entity.Order;
import alatoo.edu.kg.order_system.store.entity.OrderItem;
import alatoo.edu.kg.order_system.store.repositories.OrderItemRepository;
import alatoo.edu.kg.order_system.store.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final OrderItemMapper orderItemMapper;

    public OrderItemServiceImpl(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper, OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional
    @Override
    public OrderItemDTO createOrderItem(Long orderId, OrderItemDTO orderItemDto) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        OrderItem orderItem = orderItemMapper.toEntity(orderItemDto);
        orderItem.setOrder(order);

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
        return orderItemMapper.toDto(savedOrderItem);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderItemDTO> getItemsByOrderId(Long orderId) {
        return orderItemRepository.findByOrderId(orderId).stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OrderItemDTO getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .map(orderItemMapper::toDto)
                .orElseThrow(() -> new OrderItemNotFoundException(id));
    }

    @Transactional
    @Override
    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDto) {
        OrderItem existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new OrderItemNotFoundException(id));

        existingOrderItem.setDescription(orderItemDto.description());
        existingOrderItem.setPrice(orderItemDto.price());

        OrderItem updatedOrderItem = orderItemRepository.save(existingOrderItem);
        return orderItemMapper.toDto(updatedOrderItem);
    }

    @Transactional
    @Override
    public void deleteOrderItem(Long id) {
        orderItemRepository.deleteById(id);
    }


}
