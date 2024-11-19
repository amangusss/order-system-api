package alatoo.edu.kg.order_system.api.payload;

public record OrderItemDTO(
        Long id,
        String description,
        Double price,
        Long orderId
) {
}
