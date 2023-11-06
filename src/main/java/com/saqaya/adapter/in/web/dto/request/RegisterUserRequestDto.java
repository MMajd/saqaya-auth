package com.saqaya.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterUserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private Boolean marketingConsent;
}
