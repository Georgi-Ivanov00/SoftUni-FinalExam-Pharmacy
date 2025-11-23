package main.Orders.model;

import jakarta.persistence.*;
import lombok.*;
import main.OrderItem.model.OrderItem;
import main.User.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime createdOn;
    @Enumerated(EnumType.STRING)
    private Status orderStatus;
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();
    @ManyToOne
    private User user;
}
