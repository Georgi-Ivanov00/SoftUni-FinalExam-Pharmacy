package main.OrderItem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import main.Orders.model.Order;
import main.Products.model.products;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private products product;
    private double price;
    private double totalPrice;
}
