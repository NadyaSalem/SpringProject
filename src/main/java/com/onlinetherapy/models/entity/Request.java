package com.onlinetherapy.models.entity;

import com.onlinetherapy.models.enums.RequestStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "requests")
public class Request extends BaseEntity {

    @Column(nullable = false, name = "date_requested")
    private LocalDate dateRequested;

    @Column(name = "request_sum")
    private BigDecimal RequestSum;

    @ManyToOne
    private User client;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatusEnum requestStatusEnum;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "appointments_requests",
            joinColumns = @JoinColumn(
                    name = "request_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "purchase_appointment_id",
                    referencedColumnName = "id"))
    private List<PurchasedAppointments> requestAppointments = new ArrayList<> ();

    @OneToOne
    private Message message;

}
