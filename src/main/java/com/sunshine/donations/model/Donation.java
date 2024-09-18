package com.sunshine.donations.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private BigDecimal currentAmountReceived = BigDecimal.valueOf(0); //actual amount received from all the donors
    @ManyToOne
    @JoinColumn(name = "fundraiser_id")
    @JsonIgnore
    private User fundraiser; //the user that requested/initiated the donations
    @JsonIgnore
    @OneToMany(mappedBy = "donation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Gift> gifts = new ArrayList<>(); //list of all the gifts offered by the donors
    private boolean isCompleted = false; //to check if the target amount has reached the desired value
    @CreatedDate
    private LocalDateTime createdAt; //date at which the fundraiser initiated the donations
    @UpdateTimestamp
    private LocalDateTime updateAt; //date at which the fundraiser updated the donations info

    public void setIsCompleted(){
        //if the currentAmountReceived >= target amount automatically set the donation to be completed
        this.isCompleted = this.currentAmountReceived.compareTo(this.targetAmount) >= 0;
    }
}
