package com.example.planmyevent.dtos;

import jakarta.validation.constraints.NotNull;

public class DeleteEventDTO {
    @NotNull(message = "Enter valid User ID.")
    private Long userId;

    @NotNull(message = "Enter valid Event ID.")
    private Long eventId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
