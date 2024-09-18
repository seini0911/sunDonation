package com.sunshine.donations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, length = 200, name = "name")
    private String name;
    @Column(nullable = false, length = 120, name = "email")
    private String email;
    @Column(nullable = false, length = 12, name = "phone")
    private String phone;
    @Column(nullable = true, name = "location")
    private String location; //user's city/town/country
    @JsonIgnore
    @OneToMany(mappedBy = "fundraiser", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Donation> donations = new ArrayList(); //by default a user has no donations request
    @JsonIgnore
    @CreatedDate
    private LocalDateTime createdAt;
    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
