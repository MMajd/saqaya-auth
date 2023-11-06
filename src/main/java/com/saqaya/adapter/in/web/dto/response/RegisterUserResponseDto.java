package com.saqaya.adapter.in.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class RegisterUserResponseDto {
    private String id;
    private String accessToken;
}
