package com.example.WebScraping;

import java.util.ArrayList;
import com.example.*;

public class TestScraper {
    public static void main(String[] args) {
        MoodleScraper moodle = new MoodleScraper();

        // REPLACE WITH YOUR REAL CREDENTIALS TO TEST
        // BUT DO NOT SHARE THIS FILE AFTERWARDS
        boolean success = moodle.connect("22402070", "Yacema397500");

        if (success) {
            System.out.println("We are in! Ready to scrape assignments.");

            ArrayList<CalendarEvent> assignments = moodle.fetchEvents();
            System.out.println("Found " + assignments.size() + " assignments.");
        }
    }
}
