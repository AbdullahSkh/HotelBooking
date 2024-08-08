package com.hotelbooking.repository;

import com.hotelbooking.model.Restaurant.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
