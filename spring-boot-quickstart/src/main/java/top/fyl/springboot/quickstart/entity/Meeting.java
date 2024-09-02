package top.fyl.springboot.quickstart.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author dfysa
 * @data 2024/9/2 下午4:24
 * @description
 */
public class Meeting {
    private Long id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime   endTime;

    public LocalDate getDATE() {
        return date;
    }

    public void setDATE(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Meeting(LocalDate date, LocalTime endTime, Long id, LocalTime startTime) {
        this.date = date;
        this.endTime = endTime;
        this.id = id;
        this.startTime = startTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public boolean isOverlapping(Meeting other){
        return this.date.equals(other.date) && (this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime));
    }
}
