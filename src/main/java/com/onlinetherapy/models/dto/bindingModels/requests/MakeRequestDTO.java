package com.onlinetherapy.models.dto.bindingModels.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MakeRequestDTO {

    @NotBlank
    @Size(min = 10)
    private String address;

    @NotBlank
    @Pattern(regexp="08[7-9][0-9]{7}")
    private String phoneNumber;
}
