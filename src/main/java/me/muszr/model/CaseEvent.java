package me.muszr.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;

import me.muszr.model.enums.CaseEventStatus;
import me.muszr.model.enums.CaseEventType;

public class CaseEvent {

    private final int id;
    private final LocalDate testDate;
    private final LocalDate dateOfBirth;
    private final String name;
    private final String location;
    private final int age;
    private final CaseEventStatus status;
    private final CaseEventType type;

    @JsonCreator
    public CaseEvent(int id, LocalDate testDate, LocalDate dateOfBirth, String name, String location, int age,
            CaseEventStatus status, CaseEventType type) {
        this.id = id;
        this.testDate = testDate;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.location = location;
        this.age = age;
        this.status = status;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getAge() {
        return age;
    }

    public CaseEventStatus getStatus() {
        return status;
    }

    public CaseEventType getType() {
        return type;
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("Id: ");
        stringBuilder.append(id);
        stringBuilder.append(" | TestLocalDate: ");
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
        stringBuilder.append(" | Type: ");
        stringBuilder.append(type);
        return stringBuilder.toString();
    }
}
