package com.sunshine.donations.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User donor;// The user who gave out for a donation, a user can have many gifts made out
    private BigDecimal amount; //Amount given by the user for a donation
    @ManyToOne
    @JoinColumn(name = "donation_id")
    private Donation donation;//Donation for which the gift has been created
    private LocalDateTime date; //date at which the gift was made
}
