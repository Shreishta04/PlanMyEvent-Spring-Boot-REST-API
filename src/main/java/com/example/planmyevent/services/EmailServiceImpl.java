package com.example.planmyevent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.task.TaskSchedulingProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailServices{

    @Autowired
    JavaMailSender javaMailSender;

    public void sendReminderEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(System.getenv("EMAIL_USERNAME"));;
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        System.out.println("📧 Sending email to " + to);
        try {
            javaMailSender.send(message);
            System.out.println("Email sent!");
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
        }
    }
}
