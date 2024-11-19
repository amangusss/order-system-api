package alatoo.edu.kg.user_system.api.payload.order;

public record OrderItemDTO(
        Long id,
        String description,
        Double price,
        Long orderId
) {}
