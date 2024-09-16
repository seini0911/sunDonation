package com.sunshine.donations.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false, length = 120)
    private String email;
    @Column(nullable = false, length = 12)
    private String phone;
    @Column(nullable = true)
    private String location;
}
