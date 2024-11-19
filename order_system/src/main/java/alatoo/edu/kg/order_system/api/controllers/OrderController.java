package alatoo.edu.kg.order_system.api.controllers;

import alatoo.edu.kg.order_system.api.payload.OrderDTO;
import alatoo.edu.kg.order_system.api.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    public OrderController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDto) {
        log.info("Received order for creation: {}", orderDto);
        try {
            OrderDTO createdOrder = orderService.createOrder(orderDto);
            log.info("Order successfully created: {}", createdOrder);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occurred while creating the order: {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        log.info("Fetching order with ID: {}", id);
        try {
            OrderDTO orderDto = orderService.getOrderById(id);
            log.info("Order fetched successfully: {}", orderDto);
            return new ResponseEntity<>(orderDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while fetching the order with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDto) {
        log.info("Updating order with ID: {}. Update data: {}", id, orderDto);
        try {
            OrderDTO updatedOrder = orderService.updateOrder(id, orderDto);
            log.info("Order successfully updated: {}", updatedOrder);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while updating the order with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        log.info("Fetching all orders");
        try {
            List<OrderDTO> orders = orderService.getAllOrders();
            log.info("Successfully fetched all orders. Total count: {}", orders.size());
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while fetching all orders: {}", e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        log.info("Deleting order with ID: {}", id);
        try {
            orderService.deleteOrder(id);
            log.info("Order successfully deleted with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("Error occurred while deleting the order with ID {}: {}", id, e.getMessage(), e);
            throw e;
        }
    }
}
