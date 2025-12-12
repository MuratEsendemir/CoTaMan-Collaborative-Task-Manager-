package com.example;

import java.util.ArrayList;
import java.util.List;

public class RecommendationSystem {

    /**
     * Links ExamEvents to ArchiveFiles.
     * Algorithm: Keyword matching between Exam Topics and File Titles.
     */
    public List<AcademicFile> getSuggestedMaterials(ExamEvent exam, Group group) {
        List<String> topics = exam.getTopics(); // Assumes getter in ExamEvent
        List<AcademicFile> suggestions = new ArrayList<>();
        List<AcademicFile> groupFiles = group.getGroupArchive();

        if (groupFiles != null && topics != null) {
            for (AcademicFile file : groupFiles) {
                for (String topic : topics) {
                    // Case-insensitive check
                    if (file.getFileName().toLowerCase().contains(topic.toLowerCase())) {
                        suggestions.add(file);
                        break; // Found a match for this file, move to next file
                    }
                }
            }
        }
        return suggestions;
    }
}