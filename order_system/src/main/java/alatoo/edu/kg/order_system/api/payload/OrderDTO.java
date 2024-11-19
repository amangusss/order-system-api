package alatoo.edu.kg.order_system.api.payload;

import java.util.List;

public record OrderDTO(
        Long id,
        Double overallPrice,
        Integer orderNumber,
        List<OrderItemDTO> orderItems
) {}
