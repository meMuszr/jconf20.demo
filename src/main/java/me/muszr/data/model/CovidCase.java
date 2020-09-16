package me.muszr.data.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;

import io.micronaut.core.annotation.Creator;
import io.micronaut.data.annotation.DateUpdated;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.Transient;
import me.muszr.data.model.enums.CaseStatus;

@MappedEntity
public class CovidCase {
    @Id
    @GeneratedValue
    private Integer id;
    private long sourceId;
    private LocalDate testDate;
    private LocalDate dateOfBirth;
    private String name;
    private String location;
    private CaseStatus status;
    @DateUpdated
    private Instant lastUpdated;

    @Creator
    public CovidCase(long sourceId, LocalDate testDate, LocalDate dateOfBirth, String name, String location,
            CaseStatus status) {
        this.sourceId = sourceId;
        this.testDate = testDate;
        this.dateOfBirth = dateOfBirth;
        this.name = name;
        this.location = location;
        this.status = status;
    }
    public Integer getId() {
        return id;
    }


    public long getSourceId() {
        return sourceId;
    }

    @Transient
    public int getAge() {
        var now = LocalDate.now();
        return Period.between(dateOfBirth, now).getYears();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getTestDate() {
        return testDate;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public CaseStatus getStatus() {
        return status;
    }


    public Instant getLastUpdated() {
        return lastUpdated;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStatus(CaseStatus status) {
        this.status = status;
    }
    
    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
