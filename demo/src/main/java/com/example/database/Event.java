package com.example.database;

import org.bson.types.ObjectId;

public class Event {
    private ObjectId id;
    private ObjectId ownerId; // Bu etkinlik kime ait?
    private String title;
    private String location;
    private Importance importance; // Enum kullanıyoruz
    private String startTime;
    private String endTime;

    public Event() {} // MongoDB için zorunlu

    public Event(ObjectId ownerId, String title, String location, Importance importance, String startTime, String endTime) {
        this.ownerId = ownerId;
        this.title = title;
        this.location = location;
        this.importance = importance;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getter ve Setter'lar (VS Code'da sağ tık > Source Action > Generate Getters and Setters diyerek hızlıca yapabilirsin)
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public ObjectId getOwnerId() { return ownerId; }
    public void setOwnerId(ObjectId ownerId) { this.ownerId = ownerId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Importance getImportance() { return importance; }
    public void setImportance(Importance importance) { this.importance = importance; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    @Override
    public String toString() {
        return title + " (" + importance + ")";
    }
}