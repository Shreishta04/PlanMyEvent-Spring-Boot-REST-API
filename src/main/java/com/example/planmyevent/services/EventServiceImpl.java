package com.example.planmyevent.services;

import com.example.planmyevent.dtos.CreateEventDTO;
import com.example.planmyevent.dtos.DeleteEventDTO;
import com.example.planmyevent.dtos.UpdateEventDTO;
import com.example.planmyevent.exceptions.ResourceNotFoundException;
import com.example.planmyevent.exceptions.UnauthorizedUserException;
import com.example.planmyevent.models.Event;
import com.example.planmyevent.models.Users;
import com.example.planmyevent.repositories.EventRepository;
import com.example.planmyevent.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventServices {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) throws ResourceNotFoundException {
        return eventRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Event with ID: " + id + " not found."));
    }

    public Event createEvent(CreateEventDTO createEventDTO) throws ResourceNotFoundException {
        Users user = userRepository.findById(createEventDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Event with ID: " + createEventDTO.getUserId() + " not found."));
        Event event = new Event();
        event.setTitle(createEventDTO.getTitle());
        event.setDescription(createEventDTO.getDescription());
        event.setVenue(createEventDTO.getVenue());
        event.setDateTime(createEventDTO.getDateTime());
        event.setCity(createEventDTO.getCity());
        event.setLatitude(createEventDTO.getLatitude());
        event.setLongitude(createEventDTO.getLongitude());
        event.setUser(user);
        user.getEventList().add(event);
        userRepository.save(user);
        return eventRepository.save(event);
    }

    public Event updateEvent(UpdateEventDTO updateEventDTO) throws ResourceNotFoundException {
        Users user = userRepository.findById(updateEventDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Event with ID: " + updateEventDTO.getUserId() + " not found."));
        Event event = eventRepository.findById(updateEventDTO.getEventId()).orElseThrow(() -> new ResourceNotFoundException("Event with ID: " + updateEventDTO.getEventId() + " not found."));
        if (!event.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedUserException("User Unauthorized to update Event details.");
        }
        if (updateEventDTO.getTitle() != null) {
            event.setTitle(updateEventDTO.getTitle());
        }
        if (updateEventDTO.getDescription() != null) {
            event.setDescription(updateEventDTO.getDescription());
        }
        if (updateEventDTO.getVenue() != null) {
            event.setVenue(updateEventDTO.getVenue());
        }
        if (updateEventDTO.getDateTime() != null) {
            event.setDateTime(updateEventDTO.getDateTime());
        }
        if (updateEventDTO.getCity() != null) {
            event.setCity(updateEventDTO.getCity());
        }
        if (updateEventDTO.getLatitude() != null) {
            event.setLatitude(updateEventDTO.getLatitude());
        }
        if (updateEventDTO.getLongitude() != null) {
            event.setLongitude(updateEventDTO.getLongitude());
        }
        return eventRepository.save(event);
    }

    public String deleteEvent(DeleteEventDTO deleteEventDTO) throws ResourceNotFoundException {
        Users user = userRepository.findById(deleteEventDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("Event with ID: " + deleteEventDTO.getUserId() + " not found."));
        Event event = eventRepository.findById(deleteEventDTO.getEventId()).orElseThrow(() -> new ResourceNotFoundException("Event with ID: " + deleteEventDTO.getEventId() + " not found."));
        if (!event.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedUserException("User Unauthorized to update Event details.");
        }
        eventRepository.delete(event);
        return "Event with ID: " + deleteEventDTO.getEventId() + " deleted!";
    }
}
