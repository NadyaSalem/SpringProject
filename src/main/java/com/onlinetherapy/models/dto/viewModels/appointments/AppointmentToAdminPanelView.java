package com.onlinetherapy.models.dto.viewModels.appointments;

import com.onlinetherapy.models.entity.Therapist;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AppointmentToAdminPanelView {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Therapist therapist;
}
