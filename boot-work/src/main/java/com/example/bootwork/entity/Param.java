package com.example.bootwork.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Param {

    private LocalDate startDate;

    private LocalDate endDate;

    public LocalDate getStartDate() {
        if(startDate == null){
            startDate = LocalDate.now().minusDays(6);
        }
        return startDate;
    }

    public LocalDate getEndDate() {
        if(endDate == null){
            endDate = LocalDate.now();
        }
        return endDate;
    }
}
