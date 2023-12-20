package com.onlinetherapy.models.dto.bindingModels.appointments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CreateAppointmentDTO {

    @NotBlank
    @Size(min = 5)
    private String name;

    @NotBlank
    @Size(min = 20)
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;

    private MultipartFile img;

    @NotNull
    private String therapist;

}
