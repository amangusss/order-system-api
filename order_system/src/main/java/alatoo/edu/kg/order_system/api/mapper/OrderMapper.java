package alatoo.edu.kg.order_system.api.mapper;


import alatoo.edu.kg.order_system.api.payload.OrderDTO;
import alatoo.edu.kg.order_system.store.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;


@Mapper(componentModel = SPRING, uses = OrderItemMapper.class)
public interface OrderMapper {

    @Mapping(source = "orderItems", target = "orderItems")
    OrderDTO toDto(Order order);

    @Mapping(target = "orderItems", ignore = true)
    Order toEntity(OrderDTO orderDto);
}
