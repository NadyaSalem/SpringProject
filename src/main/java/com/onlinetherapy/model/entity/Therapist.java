package com.onlinetherapy.model.entity;

import com.onlinetherapy.model.enums.TherapistType;
import jakarta.persistence.*;

import java.util.Set;

@Table(name = "therapists")
@Entity
public class Therapist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private TherapistType therapistType;

    @Column(nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "therapist")
    private Set<Appointment> appointments;

    public Therapist() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TherapistType getTherapistType() {
        return therapistType;
    }

    public void setTherapistType(TherapistType therapistType) {
        this.therapistType = therapistType;
        this.setDescription(therapistType);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(TherapistType name) {
        String description = "";

        switch (name) {
            case INDIVIDUAL_COUNSELOR -> description = "Provide personalized, one-on-one support to help clients address a wide range of mental health concerns.";
            case FAMILY_COUNSELOR-> description = "Help families and couples strengthen relationships, improve communication, and resolve conflicts in a safe and supportive setting." ;
            case ORGANIZATIONAL_COUNSELOR -> description = " Organizational psychologists help workplaces run smoothly and with more success. They help to build organizational infrastructures that benefit the people and the processes as well as help to resolve conflicts when they arise." ;

        }

        this.description = description;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
