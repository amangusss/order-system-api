package alatoo.edu.kg.order_system.api.mapper;

import alatoo.edu.kg.order_system.api.payload.OrderItemDTO;
import alatoo.edu.kg.order_system.store.entity.Order;
import alatoo.edu.kg.order_system.store.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OrderItemMapper {
    @Mapping(source = "order.id", target = "orderId")
    OrderItemDTO toDto(OrderItem orderItem);

    @Mapping(target = "order", source = "orderId")
    OrderItem toEntity(OrderItemDTO orderItemDto);

    default Order map(Long orderId) {
        if (orderId == null) return null;

        return Order.builder()
                .id(orderId)
                .build();
    }
}

