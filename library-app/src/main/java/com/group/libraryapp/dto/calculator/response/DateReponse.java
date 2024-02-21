package com.group.libraryapp.dto.calculator.response;

import java.time.LocalDate;
import java.time.DayOfWeek;

public class DateReponse {
    private final LocalDate dayOfTheWeek;

    public DateReponse(String dayOfTheWeek) {
        this.dayOfTheWeek = LocalDate.parse(dayOfTheWeek);
    }

    public DayOfWeek getDayOfTheWeek() {
        return dayOfTheWeek.getDayOfWeek();
    }
}
