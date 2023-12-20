package com.onlinetherapy.models.dto.viewModels.requests;

import com.onlinetherapy.models.entity.Message;
import com.onlinetherapy.models.entity.PurchasedAppointments;
import com.onlinetherapy.models.entity.User;
import com.onlinetherapy.models.enums.RequestStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class CloseRequestToAdminPanelView {
    private Long id;
    private LocalDate dateRequested;
    private BigDecimal requestSum;
    private User client;
    private RequestStatusEnum requestStatus;
    private List<PurchasedAppointments> requestedAppointments;
    private Message message;
}
