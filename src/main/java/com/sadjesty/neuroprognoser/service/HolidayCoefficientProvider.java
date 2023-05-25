package com.sadjesty.neuroprognoser.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;

@Service
public class HolidayCoefficientProvider implements ParameterProvider{

    @Override
    public double provideParameter() {
        LocalDate today = LocalDate.now();
        MonthDay currentMonthDay = MonthDay.of(today.getMonth(), today.getDayOfMonth());
            for (Holiday holiday : Holiday.values()) {
                if (holiday.getDate().equals(currentMonthDay)) {
                    return holiday.getCoefficient();
                }
            }

            DayOfWeek dayOfWeek = today.getDayOfWeek();
            if (DayOfWeek.SATURDAY.equals(dayOfWeek) || DayOfWeek.SUNDAY.equals(dayOfWeek)) {
                return 0.2;
            }
            return 0.0;
    }

    public enum Holiday {

        NEW_YEAR(MonthDay.of(12, 31), 1.0),
        BLACK_FRIDAY(MonthDay.of(11, 24), 1.0),
        KNOWLEDGE_DAY(MonthDay.of(9, 1), 1.0),
        DEFENDER_DAY(MonthDay.of(2, 23), 1.0),
        WOMEN_DAY(MonthDay.of(3, 8), 1.0),
        VALENTINES_DAY(MonthDay.of(2, 14), 1.0),
        HALLOWEEN(MonthDay.of(10, 31), 0.5),
        CYBER_MONDAY(MonthDay.of(11, 27), 0.5),
        VICTORY_DAY(MonthDay.of(5, 9), 0.5),
        UNIT_DAY(MonthDay.of(11, 4), 0.5),
        EASTER(MonthDay.of(5, 5), 0.5),
        CHILDREN_DAY(MonthDay.of(6, 1), 0.5),
        RUSSIA_DAY(MonthDay.of(6, 12), 0.5);

        private final MonthDay date;
        private final Double coefficient;

        Holiday(MonthDay date, Double coefficient) {
            this.date = date;
            this.coefficient = coefficient;
        }

        public MonthDay getDate() {
            return date;
        }

        public Double getCoefficient() {
            return coefficient;
        }
    }
}
