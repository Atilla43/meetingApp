package com.example.meetingapp.Model;
import java.time.LocalDateTime;

public class Meeting {
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime reminderTime;

    public Meeting(String title, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime reminderTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.reminderTime = reminderTime;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getReminderTime() {
        return reminderTime;
    }

    @Override
    public String toString() {
        return title + " (с " + startTime + " до " + endTime + ")";
    }
}

