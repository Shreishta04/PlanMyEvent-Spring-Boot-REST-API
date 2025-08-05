package com.example.planmyevent.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class CreateEventDTO {
    @NotNull(message = "Enter valid User ID.")
    private Long userId;

    @NotBlank(message = "Title is required.")
    private String title;

    @NotBlank(message = "Description is required.")
    private String description;

    @NotNull(message = "Date and Time is required.")
    private LocalDateTime dateTime;

    @NotBlank(message = "Venue is required.")
    private String venue;

    @NotBlank(message = "City is required.")
    private String city;

    private Double longitude;
    private Double latitude;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getCity() {
        return city;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
