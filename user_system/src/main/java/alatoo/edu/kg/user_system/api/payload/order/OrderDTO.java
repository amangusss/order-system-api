package alatoo.edu.kg.user_system.api.payload.order;

import java.util.List;

public record OrderDTO(
        Long id,
        Double overallPrice,
        Integer orderNumber,
        List<OrderItemDTO> orderItems
) {}

