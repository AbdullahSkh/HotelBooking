package com.hotelbooking.repository;

import com.hotelbooking.model.Restaurant.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
