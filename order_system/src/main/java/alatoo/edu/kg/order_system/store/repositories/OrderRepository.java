package alatoo.edu.kg.order_system.store.repositories;

import alatoo.edu.kg.order_system.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
