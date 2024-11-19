package alatoo.edu.kg.order_system.api.services;

import alatoo.edu.kg.order_system.api.payload.OrderItemDTO;

import java.util.List;

public interface OrderItemService {
    OrderItemDTO createOrderItem(Long id, OrderItemDTO orderItemDto);
    List<OrderItemDTO> getItemsByOrderId(Long orderId);
    OrderItemDTO getOrderItemById(Long id);
    void deleteOrderItem(Long id);
    OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDto);

}
