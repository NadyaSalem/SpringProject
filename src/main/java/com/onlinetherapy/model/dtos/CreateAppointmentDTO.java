package com.onlinetherapy.model.dtos;

import com.onlinetherapy.model.entity.UserEntity;
import com.onlinetherapy.model.enums.TherapistType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CreateAppointmentDTO {

    @Future(message = "The input date must be in the future!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "You must select a date!")
    private LocalDate inputDate;
    private UserEntity bookedBy;
    @NotNull(message = "You must select a language!")
    private TherapistType therapist;

    public CreateAppointmentDTO() {
    }

    public CreateAppointmentDTO(LocalDate inputDate, TherapistType therapist) {
        this.inputDate = inputDate;
        this.therapist = therapist;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDateTime(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public TherapistType getTherapist() {
        return therapist;
    }

    public void setTherapist(TherapistType therapist) {
        this.therapist = therapist;
    }

    public UserEntity getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(UserEntity bookedBy) {
        this.bookedBy = bookedBy;
    }
}
