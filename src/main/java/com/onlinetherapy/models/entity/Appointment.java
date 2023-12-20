package com.onlinetherapy.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column(updatable=true,nullable = false)
    private BigDecimal price;

    @Column(updatable=true, nullable=false, unique = true)
    private String img;

    @Column(nullable = false)
    @Length(max = 5000)
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Therapist therapist;

    public String getName() {
        return name;
    }

    public Appointment setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Appointment setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImg() {
        return img;
    }

    public Appointment setImg(String img) {
        this.img = img;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Appointment setDescription(String description) {
        this.description = description;
        return this;
    }

    public Therapist getTherapist() {
        return therapist;
    }

    public Appointment setTherapist(Therapist therapist) {
        this.therapist = therapist;
        return this;
    }
}
