package com.sunshine.donations.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDonationRequest {
    @NotNull(message = "enter a target amount for the donation")
    private Long targetAmount;
    @NotNull(message = "provide a fundraiser/initiator id")
    private Long fundraiserId;
}
