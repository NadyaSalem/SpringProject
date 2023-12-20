package com.onlinetherapy.models.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Entity
@Table(name = "messages")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity  {

    @Column(nullable = false)
    @Length(max = 5000)
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private User author;
}
