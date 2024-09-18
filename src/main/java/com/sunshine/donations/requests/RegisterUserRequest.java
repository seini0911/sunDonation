package com.sunshine.donations.requests;

import com.sunshine.donations.model.Donation;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "email is required")
    private String email;
    @NotNull(message = "phone is required")
    private String phone;
    private String location;
}
