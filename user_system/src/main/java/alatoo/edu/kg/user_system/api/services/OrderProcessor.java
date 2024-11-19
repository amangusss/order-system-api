package alatoo.edu.kg.user_system.api.services;

import alatoo.edu.kg.user_system.api.payload.order.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class OrderProcessor {

    private static final Logger log = LoggerFactory.getLogger(OrderProcessor.class);
    private static final int MAX_SIZE = 50;

    private final CopyOnWriteArrayList<OrderDTO> processedOrders = new CopyOnWriteArrayList<>();

    public void saveOrderTemporarily(OrderDTO orderDto) {
        synchronized (this) {
            if (processedOrders.size() >= MAX_SIZE) {
                OrderDTO removedOrder = processedOrders.remove(0);
                log.debug("Oldest order removed to maintain max size: {}", removedOrder);
            }
            processedOrders.add(orderDto);
            log.info("Order temporarily saved. Current size: {}. Order details: {}", processedOrders.size(), orderDto);
        }
    }

    public List<OrderDTO> getProcessedOrders() {
        log.debug("Fetching all processed orders. Current size: {}", processedOrders.size());
        return Collections.unmodifiableList(processedOrders);
    }

    public void clearProcessedOrders() {
        synchronized (this) {
            processedOrders.clear();
            log.info("All processed orders have been cleared.");
        }
    }

    public int getProcessedOrdersCount() {
        int size = processedOrders.size();
        log.debug("Current processed orders count: {}", size);
        return size;
    }
}
