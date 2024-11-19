package alatoo.edu.kg.order_system.api.controllers;

import alatoo.edu.kg.order_system.api.payload.OrderItemDTO;
import alatoo.edu.kg.order_system.api.services.OrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    private static final Logger log = LoggerFactory.getLogger(OrderItemController.class);
    private final OrderItemService orderItemService;

    public OrderItemController(final OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/{orderId}/items")
    public ResponseEntity<OrderItemDTO> createOrderItem(@PathVariable Long orderId,
                                                        @RequestBody OrderItemDTO orderItemDto) {
        log.info("Received request to create order item for order ID {}: {}", orderId, orderItemDto);
        try {
            OrderItemDTO createdOrderItem = orderItemService.createOrderItem(orderId, orderItemDto);
            log.info("Order item successfully created: {}", createdOrderItem);
            return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred while creating order item for order ID {}: {}", orderId, e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemDTO> getOrderItemById(@PathVariable Long id) {
        log.info("Fetching order item with ID: {}", id);
        try {
            OrderItemDTO orderItemDto = orderItemService.getOrderItemById(id);
            log.info("Order item fetched successfully: {}", orderItemDto);
            return new ResponseEntity<>(orderItemDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while fetching order item with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemDTO orderItemDto) {
        log.info("Updating order item with ID: {}. Update data: {}", id, orderItemDto);
        try {
            OrderItemDTO updatedOrderItem = orderItemService.updateOrderItem(id, orderItemDto);
            log.info("Order item successfully updated: {}", updatedOrderItem);
            return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while updating order item with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getItemsByOrderId(@RequestParam Long orderId) {
        log.info("Fetching all order items for order ID: {}", orderId);
        try {
            List<OrderItemDTO> orderItems = orderItemService.getItemsByOrderId(orderId);
            log.info("Successfully fetched all order items for order ID {}. Total count: {}", orderId, orderItems.size());
            return new ResponseEntity<>(orderItems, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while fetching order items for order ID {}: {}", orderId, e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable Long id) {
        log.info("Deleting order item with ID: {}", id);
        try {
            orderItemService.deleteOrderItem(id);
            log.info("Order item successfully deleted with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error occurred while deleting order item with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}
