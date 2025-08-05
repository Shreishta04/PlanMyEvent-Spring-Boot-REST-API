package com.example.planmyevent.services;

import com.example.planmyevent.dtos.CreateEventDTO;
import com.example.planmyevent.dtos.DeleteEventDTO;
import com.example.planmyevent.dtos.UpdateEventDTO;
import com.example.planmyevent.exceptions.ResourceNotFoundException;
import com.example.planmyevent.exceptions.UnauthorizedUserException;
import com.example.planmyevent.models.Event;
import com.example.planmyevent.models.Users;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
@Service
public interface EventServices {

    public List<Event> getAllEvents();

    public Event getEventById(Long id) throws ResourceNotFoundException;

    public Event createEvent(CreateEventDTO createEventDTO) throws ResourceNotFoundException;

    public Event updateEvent(UpdateEventDTO updateEventDTO) throws ResourceNotFoundException;

    public String deleteEvent(DeleteEventDTO deleteEventDTO) throws ResourceNotFoundException;

}
