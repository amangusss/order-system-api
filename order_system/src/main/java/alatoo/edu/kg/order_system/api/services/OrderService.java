package alatoo.edu.kg.order_system.api.services;

import alatoo.edu.kg.order_system.api.payload.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO order);
    OrderDTO getOrderById(Long id);
    List<OrderDTO> getAllOrders();
    void deleteOrder(Long id);
    OrderDTO updateOrder(Long id, OrderDTO orderDto);
}
