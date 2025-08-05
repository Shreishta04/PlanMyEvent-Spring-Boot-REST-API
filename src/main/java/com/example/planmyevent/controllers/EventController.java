package com.example.planmyevent.controllers;

import com.example.planmyevent.dtos.CreateEventDTO;
import com.example.planmyevent.dtos.DeleteEventDTO;
import com.example.planmyevent.dtos.UpdateEventDTO;
import com.example.planmyevent.dtos.WeatherRequestDTO;
import com.example.planmyevent.exceptions.ResourceNotFoundException;
import com.example.planmyevent.models.Event;
import com.example.planmyevent.services.EventServiceImpl;
import com.example.planmyevent.utils.WeatherUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    @Autowired
    private EventServiceImpl eventServicesImpl;

    @Autowired
    private WeatherUtil weatherUtil;

    @GetMapping()
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventServicesImpl.getAllEvents();
        return new ResponseEntity<>(events, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        try {
            Event event = eventServicesImpl.getEventById(id);
            return new ResponseEntity<>(event, HttpStatusCode.valueOf(200));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@Valid @RequestBody CreateEventDTO createEventDTO) {
        try {
            Event event = eventServicesImpl.createEvent(createEventDTO);
            return new ResponseEntity<>(event, HttpStatusCode.valueOf(200));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateEvent(@Valid @RequestBody UpdateEventDTO updateEventDTO){
        try{
            Event event = eventServicesImpl.updateEvent(updateEventDTO);
            return new ResponseEntity<>(event, HttpStatusCode.valueOf(200));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteEvent(@Valid @RequestBody DeleteEventDTO deleteEventDTO){
        try{
            String deletedEvent = eventServicesImpl.deleteEvent(deleteEventDTO);
            return new ResponseEntity<>(deletedEvent, HttpStatusCode.valueOf(200));
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(404));
        }
    }

    @PostMapping("/weather")
    public ResponseEntity<?> getWeatherForecast(@Valid @RequestBody WeatherRequestDTO weatherRequestDTO){
        String weatherForecast = weatherUtil.getWeatherResponse(weatherRequestDTO);
        return new ResponseEntity<>(weatherForecast, HttpStatusCode.valueOf(200));
    }

}
