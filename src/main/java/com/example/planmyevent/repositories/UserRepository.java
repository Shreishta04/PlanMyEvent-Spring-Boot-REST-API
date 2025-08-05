package com.example.planmyevent.repositories;

import com.example.planmyevent.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
