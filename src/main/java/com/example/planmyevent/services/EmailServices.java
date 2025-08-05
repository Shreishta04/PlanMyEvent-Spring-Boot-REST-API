package com.example.planmyevent.services;

public interface EmailServices {
    public void sendReminderEmail(String to, String subject, String body);
}
