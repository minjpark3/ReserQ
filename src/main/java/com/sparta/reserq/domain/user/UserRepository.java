package com.sparta.reserq.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

   // Optional<User> findByEmail(String email);

    //@Query("select u from User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

}
