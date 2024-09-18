package com.sunshine.donations.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateGiftRequest {
    @NotNull(message = "enter an amount")
    private Long amount;
    @NotNull(message = "donor id required for a gift")
    private Long donorId;
}
