package com.example.planmyevent.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class WeatherRequestDTO {
    @NotBlank(message = "City name cannot be blank.")
    private String city;

    @NotNull(message = "Date cannot be blank")
    private LocalDate date;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
