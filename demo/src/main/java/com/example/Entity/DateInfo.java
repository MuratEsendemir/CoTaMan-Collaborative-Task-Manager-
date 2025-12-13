package com.example.Entity;

import java.time.LocalDate;

public class DateInfo {
    private String description;
    private LocalDate date;

    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
