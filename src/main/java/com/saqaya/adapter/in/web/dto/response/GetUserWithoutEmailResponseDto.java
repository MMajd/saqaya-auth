package com.saqaya.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Data
@SuperBuilder
@Builder
public class GetUserWithoutEmailResponseDto extends GetUserResponseDto {
    private String firstName;
    private String lastName;
    private Boolean marketingConsent;
}
