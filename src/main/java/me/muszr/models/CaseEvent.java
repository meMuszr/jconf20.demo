package me.muszr.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;

public class CaseEvent {
    private final long id;
    private final Date testDate;
    private final Date dateOfBirth;
    private final String name;
    private final String location;
    private final int age;
    private final String status;

    @JsonCreator
    public CaseEvent(long id, Date testDate, Date dateOfBirth, String name, String location, int age, String status) {
        this.id = id;
        this.testDate = testDate;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.location = location;
        this.age = age;
        this.status = status;
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("Id: ");
        stringBuilder.append(id);
        stringBuilder.append(" | TestDate: ");
        stringBuilder.append(testDate);
        stringBuilder.append(" | DoB: ");
        stringBuilder.append(dateOfBirth);
        stringBuilder.append(" | Name: ");
        stringBuilder.append(name);
        stringBuilder.append(" | Location: ");
        stringBuilder.append(location);
        stringBuilder.append(" | Age: ");
        stringBuilder.append(age);
        stringBuilder.append(" | Status: ");
        stringBuilder.append(status);
        return stringBuilder.toString();
    }

}
