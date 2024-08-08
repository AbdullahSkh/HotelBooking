package com.hotelbooking.model.Hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotelbooking.model.Restaurant.OrderItem;
import com.hotelbooking.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User customer;

    @JsonIgnore
    @ManyToOne
    private Hotel hotel;

    private Long totalAmount;

    private String orderStatus;

    private Date createdAt;

    @OneToMany
    private List<OrderItem> items;

    //private Payment payment;
    private int totalItem;

    private int totalPrice;
}
