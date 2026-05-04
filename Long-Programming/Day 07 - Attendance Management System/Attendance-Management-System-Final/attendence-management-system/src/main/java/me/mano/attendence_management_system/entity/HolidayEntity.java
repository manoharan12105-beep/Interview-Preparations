package me.mano.attendence_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "holidays")
public class HolidayEntity {

    @Id
    private LocalDate date;

    public HolidayEntity() {
    }

    public HolidayEntity(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
