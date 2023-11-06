package com.saqaya.adapter.in.web.service;

import com.saqaya.adapter.in.web.dto.GetUserRequestDto;
import com.saqaya.adapter.in.web.dto.GetUserResponseDto;

public interface GetUserWebService {
    GetUserResponseDto get(GetUserRequestDto dto);
}
