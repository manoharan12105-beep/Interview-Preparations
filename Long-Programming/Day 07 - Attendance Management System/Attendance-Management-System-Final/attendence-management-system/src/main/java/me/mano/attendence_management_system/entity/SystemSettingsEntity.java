package me.mano.attendence_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "system_settings")
public class SystemSettingsEntity {

    @Id
    private String id;
    
    private LocalTime defaultStartTime;
    private LocalTime defaultEndTime;
    
    private LocalDate overrideDate;
    private LocalTime overrideStartTime;
    private LocalTime overrideEndTime;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalTime getDefaultStartTime() {
        return defaultStartTime;
    }

    public void setDefaultStartTime(LocalTime defaultStartTime) {
        this.defaultStartTime = defaultStartTime;
    }

    public LocalTime getDefaultEndTime() {
        return defaultEndTime;
    }

    public void setDefaultEndTime(LocalTime defaultEndTime) {
        this.defaultEndTime = defaultEndTime;
    }

    public LocalDate getOverrideDate() {
        return overrideDate;
    }

    public void setOverrideDate(LocalDate overrideDate) {
        this.overrideDate = overrideDate;
    }

    public LocalTime getOverrideStartTime() {
        return overrideStartTime;
    }

    public void setOverrideStartTime(LocalTime overrideStartTime) {
        this.overrideStartTime = overrideStartTime;
    }

    public LocalTime getOverrideEndTime() {
        return overrideEndTime;
    }

    public void setOverrideEndTime(LocalTime overrideEndTime) {
        this.overrideEndTime = overrideEndTime;
    }
}
