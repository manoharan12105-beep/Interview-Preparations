package me.mano.attendence_management_system.dto;

import java.time.LocalTime;

public class SettingsDTO {
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean forTodayOnly;
    
    public SettingsDTO() {}

    public SettingsDTO(LocalTime startTime, LocalTime endTime, boolean forTodayOnly) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.forTodayOnly = forTodayOnly;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isForTodayOnly() {
        return forTodayOnly;
    }

    public void setForTodayOnly(boolean forTodayOnly) {
        this.forTodayOnly = forTodayOnly;
    }
}
