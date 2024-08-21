package com.example.meetingapp.service;

import com.example.meetingapp.Model.Meeting;
import java.util.ArrayList;
import java.util.List;

public class MeetingService {
    private List<Meeting> meetings = new ArrayList<>();

    public boolean addMeeting(Meeting meeting) {
        if (!isOverlapping(meeting)) {
            meetings.add(meeting);
            return true;
        }
        return false;
    }

    public boolean updateMeeting(Meeting updatedMeeting) {
        // Логика для обновления встречи
        for (int i = 0; i < meetings.size(); i++) {
            if (meetings.get(i).equals(updatedMeeting)) {
                meetings.set(i, updatedMeeting);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMeeting(Meeting meeting) {
        return meetings.remove(meeting);
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    private boolean isOverlapping(Meeting newMeeting) {
        return meetings.stream().anyMatch(meeting ->
                newMeeting.getStartTime().isBefore(meeting.getEndTime()) &&
                        newMeeting.getEndTime().isAfter(meeting.getStartTime()));
    }
}

