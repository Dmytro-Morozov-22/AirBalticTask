package com.ua.vinnytsia.airbaltic_task.repositories;

import com.ua.vinnytsia.airbaltic_task.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> getUserById(Long id);
}
