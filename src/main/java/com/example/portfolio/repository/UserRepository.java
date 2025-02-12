package com.example.portfolio.repository;

import com.example.portfolio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @SuppressWarnings("null")
    public Optional<User> findById(Long id);
}
