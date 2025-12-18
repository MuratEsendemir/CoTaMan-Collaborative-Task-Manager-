package com.example.Handlers;

import com.example.Entity.CalendarEvent;
import com.example.Entity.Importance;
import com.example.database.CloudRepository;
import com.example.database.Event; // DB Entity
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

public class CalendarInteractionHandler {

    private CloudRepository repository;

    public CalendarInteractionHandler() {
        this.repository = new CloudRepository();
    }

    public void onAddEvent(String title, int dayIndex, int timeIndex, String color) {
        // 1. Calculate Time
        LocalDateTime start = LocalDateTime.now().plusDays(dayIndex).withHour(8 + timeIndex);
        LocalDateTime end = start.plusHours(1);

        // 2. Create Application Model (for UI/Logic)
        CalendarEvent appEvent = new CalendarEvent(title, start, end, Importance.MUST);
        
        // 3. Create Database Model (for Storage)
        Event dbEvent = new Event();
        dbEvent.setTitle(appEvent.getTitle());
        dbEvent.setStartTime(appEvent.getStartTime().toString());
        dbEvent.setEndTime(appEvent.getEndTime().toString());
        // dbEvent.setImportance(appEvent.getImportance()); // Uncomment if DB Event has Enum
        dbEvent.setOwnerId(new ObjectId()); // Should be current user ID
        
        // 4. Save
        repository.saveEvent(dbEvent);
        System.out.println("Event saved to DB: " + title);
    }
}