package com.saqaya.adapter.in.web.service;

import com.saqaya.adapter.in.web.dto.RegisterUserRequestDto;
import com.saqaya.adapter.in.web.dto.RegisterUserResponseDto;

public interface RegisterUserWebService {
    RegisterUserResponseDto register(RegisterUserRequestDto dto);
}
