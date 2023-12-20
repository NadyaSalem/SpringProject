package com.onlinetherapy.models.dto.viewModels.appointments;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AppointmentWithInfoView {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String img;
}
