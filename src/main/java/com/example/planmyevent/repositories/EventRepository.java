package com.example.planmyevent.repositories;

import com.example.planmyevent.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
