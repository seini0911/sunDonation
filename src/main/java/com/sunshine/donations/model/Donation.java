package com.sunshine.donations.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal targetAmount; //desired amount to reach by donors
    private BigDecimal currentAmountReceived; //actual amount received from all the donors
    @ManyToOne
    @JoinColumn(name = "fundraiser_id")
    private User fundraiser; //the user that requested/initiated the donations
    @OneToMany(mappedBy = "donation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gift> gifts; //list of all the gifts offered by the donors
    private LocalDateTime createdAt; //date at which the fundraiser initiated the donations
    private LocalDateTime updateAt; //date at which the fundraiser updated the donations info
}
