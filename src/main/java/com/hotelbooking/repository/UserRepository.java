package com.hotelbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hotelbooking.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String username);
}
