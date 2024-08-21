package com.example.meetingapp.service;
import com.example.meetingapp.Model.Meeting;
import javafx.application.Platform;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReminderService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private Label notificationLabel; // Ссылка на Label для уведомлений

    // Конструктор, принимающий Label
    public ReminderService(Label notificationLabel) {
        this.notificationLabel = notificationLabel;
    }

    public void scheduleReminder(Meeting meeting) {
        long delay = LocalDateTime.now().until(meeting.getReminderTime(), ChronoUnit.SECONDS);
        if (delay > 0) {
            scheduler.schedule(() -> notifyUser(meeting), delay, TimeUnit.SECONDS);
        } else {
            System.out.println("Время напоминания должно быть в будущем.");
        }
    }

    private void notifyUser(Meeting meeting) {
        Platform.runLater(() -> {
            notificationLabel.setText("Напоминание: Ваша встреча '" + meeting.getTitle() + "' начнётся в " + meeting.getStartTime());
        });
    }
}
