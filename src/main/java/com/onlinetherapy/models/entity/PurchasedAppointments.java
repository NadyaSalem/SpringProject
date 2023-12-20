package com.onlinetherapy.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchase_appointments")
public class PurchasedAppointments extends BaseEntity{
    @Column(nullable = false)
    private String name;

    @Column(updatable=true,nullable = false)
    private BigDecimal price;

    @Column(updatable=true, nullable=false, unique = true)
    @Length(max = 5000)
    private String img;

    @Column
    private int count;

    @Column(name = "appointment_sum")
    private BigDecimal appointmentSum;

}
