package com.onlinetherapy.models.dto.viewModels.messages;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessagesView {
    private String description;
    private String authorFullName;

}
