package com.onlinetherapy.models.entity;

import jakarta.persistence.*;
import lombok.*;
import com.onlinetherapy.models.enums.TherapistTypeEnum;

@NoArgsConstructor
@Builder
@Entity
@Table(name = "therapists")
public class Therapist extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private TherapistTypeEnum name;

    public Therapist(TherapistTypeEnum name) {
        this.name = name;
    }

    public TherapistTypeEnum getName() {
        return name;
    }

    public Therapist setName(TherapistTypeEnum name) {
        this.name = name;
        return this;
    }
}
