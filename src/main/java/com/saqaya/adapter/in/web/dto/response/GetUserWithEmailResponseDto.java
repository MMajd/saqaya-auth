package com.saqaya.adapter.in.web.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Builder
public class GetUserWithEmailResponseDto extends GetUserWithoutEmailResponseDto {
    private String email;
}
