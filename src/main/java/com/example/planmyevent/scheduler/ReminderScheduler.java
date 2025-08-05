package com.example.planmyevent.scheduler;

import com.example.planmyevent.models.Event;
import com.example.planmyevent.models.Users;
import com.example.planmyevent.repositories.EventRepository;
import com.example.planmyevent.services.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReminderScheduler {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EmailServiceImpl emailService;

    @Scheduled(cron = "0 0 8 * * ?")
    public void sendReminder(){
        List<Event> events = eventRepository.findAll();
        LocalDateTime current = LocalDateTime.now();

        for(Event event : events){
            if(event.getDateTime() != null){
                long daysLeft = Duration.between(current, event.getDateTime()).toDays();

                if (daysLeft == 30 || daysLeft == 15 || daysLeft == 7 || daysLeft == 3 ||daysLeft == 0){
                    Users user = event.getUser();

                    if(user != null && user.getEmail() != null){
                        String subject = "Event Reminder: " + event.getTitle();

                        String body = "Hi " + user.getUsername() + ",\n\n"
                                + "This is a reminder that your event '" + event.getTitle() + "' is in "
                                + daysLeft + " day(s).\n"
                                + "Details:\n"
                                + "📅 Date: " + event.getDateTime() + "\n"
                                + "📍 Venue: " + event.getVenue() + ", " + event.getCity() + "\n\n"
                                + "See you there!\n\n"
                                + "- PlanMyEvent Team";
                        emailService.sendReminderEmail(user.getEmail(),subject,body);
                    }
                }
            }
        }
    }
}
