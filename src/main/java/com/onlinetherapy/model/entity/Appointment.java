package com.onlinetherapy.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "appointments")
@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "input_date", nullable = false)
    private LocalDate inputDate;

    @ManyToOne
    private Therapist therapist;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserEntity bookedBy;

    public Appointment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getInputDate() {
        return inputDate;
    }

    public void setInputDate(LocalDate inputDate) {
        this.inputDate = inputDate;
    }

    public Therapist getTherapist() {
        return therapist;
    }

    public void setTherapist(Therapist therapist) {
        this.therapist = therapist;
    }

    public UserEntity getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(UserEntity bookedBy) {
        this.bookedBy = bookedBy;
    }
}
