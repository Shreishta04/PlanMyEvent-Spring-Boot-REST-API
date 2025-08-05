package com.example.planmyevent.repositories;

import com.example.planmyevent.models.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admins, Long> {
}
