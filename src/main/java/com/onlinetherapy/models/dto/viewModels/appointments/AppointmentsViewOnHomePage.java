package com.onlinetherapy.models.dto.viewModels.appointments;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AppointmentsViewOnHomePage {
    private Long id;
    private String name;
    private BigDecimal price;
    private String img;
}
