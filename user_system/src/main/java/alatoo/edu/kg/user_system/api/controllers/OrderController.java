package alatoo.edu.kg.user_system.api.controllers;

import alatoo.edu.kg.user_system.api.payload.order.OrderDTO;
import alatoo.edu.kg.user_system.api.services.OrderProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderProcessor orderProcessor;

    public OrderController(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        log.info("Fetching all processed orders");
        try {
            List<OrderDTO> orders = orderProcessor.getProcessedOrders();
            log.info("Successfully fetched {} orders", orders.size());
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            log.error("Error fetching orders: {}", e.getMessage(), e);
            throw e;
        }
    }

    @PostMapping
    public ResponseEntity<Void> addOrder(@RequestBody OrderDTO orderDto) {
        log.info("Adding a new order: {}", orderDto);
        try {
            orderProcessor.saveOrderTemporarily(orderDto);
            log.info("Order successfully added: {}", orderDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            log.error("Error adding order: {}", e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> clearOrders() {
        log.info("Clearing all processed orders");
        try {
            orderProcessor.clearProcessedOrders();
            log.info("All processed orders successfully cleared");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error clearing orders: {}", e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getOrderCount() {
        log.info("Fetching the count of processed orders");
        try {
            int count = orderProcessor.getProcessedOrdersCount();
            log.info("Successfully fetched the count: {}", count);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            log.error("Error fetching order count: {}", e.getMessage(), e);
            throw e;
        }
    }
}
