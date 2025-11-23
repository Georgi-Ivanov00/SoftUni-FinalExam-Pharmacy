package main.OrderItem.repository;

import main.OrderItem.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemRepository  extends JpaRepository<OrderItem, UUID> {
}
