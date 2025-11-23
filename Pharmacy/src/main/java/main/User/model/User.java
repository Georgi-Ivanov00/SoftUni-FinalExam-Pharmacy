package main.User.model;

import jakarta.persistence.*;
import lombok.*;
import main.Orders.model.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true)
    private String email;
    private String phone;
    private String address;
    private Date dateOfBirth;
    private String profilePicture;
    private String city;
    private boolean isAdmin;
    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
}
