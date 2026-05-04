package me.mano.attendence_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReportDTO {
    private String sprno;
    private String firstName;
    private String lastName;
    private int presentDays;
    private double percentage;
}
