package com.onlinetherapy.models.dto.viewModels.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestDetailView {
    private Long id;
    private String clientFirstName;
    private String clientFullName;
    private String clientAddress;
    private BigDecimal requestSum;
}
