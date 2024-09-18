package com.sunshine.donations.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private User donor;// The user who gave out for a donation, a user can have many gifts made out
    private BigDecimal amount; //Amount given by the user for a donation
    @ManyToOne
    @JoinColumn(name = "donation_id")
    @JsonIgnore
    private Donation donation;//Donation for which the gift has been created
    @CreatedDate
    @JsonIgnore
    private LocalDateTime date; //date at which the gift was made
}
