package com.saqaya.adapter.in.web.service;

import com.saqaya.adapter.in.web.dto.request.RegisterUserRequestDto;
import com.saqaya.adapter.in.web.dto.response.RegisterUserResponseDto;

public interface RegisterUserWebService {
    RegisterUserResponseDto register(RegisterUserRequestDto dto);
}
