package com.project.parsecourse.enums;

public enum IntervalsTableEnum {


    ONE_MINUTE("one_minute_period"),
    FIVE_MINUTES("five_minutes_period"),
    TEN_MINUTES("ten_minutes_period"),
    ONE_HOUR("one_hour_period"),
    ONE_DAY("one_day_period");

    public final String tableName;


    IntervalsTableEnum(String tableName) {
        this.tableName = tableName;
    }
}
