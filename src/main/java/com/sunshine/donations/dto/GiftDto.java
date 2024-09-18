package com.sunshine.donations.dto;

import com.sunshine.donations.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GiftDto {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime date;
}
