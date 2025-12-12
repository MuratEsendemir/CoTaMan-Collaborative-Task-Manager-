package com.example;

import java.util.List;

public class Group {
    private String groupId;
    private String groupName;
    private String courseCode;
    private List<User> members;
    private List<AcademicFile> groupArchive;

    public void addMember(User newMember) { /* ... */ }
    public List<CalendarEvent> getAggregateSchedule() { /* returns combined schedule of all members */ return null; }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<AcademicFile> getGroupArchive() {
        return groupArchive;
    }

    public void setGroupArchive(List<AcademicFile> groupArchive) {
        this.groupArchive = groupArchive;
    }
}