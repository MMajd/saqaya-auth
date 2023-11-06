package com.saqaya.adapter.in.web.service;

import com.saqaya.adapter.in.web.dto.request.GetUserRequestDto;
import com.saqaya.adapter.in.web.dto.response.GetUserResponseDto;

public interface GetUserWebService {
    GetUserResponseDto get(GetUserRequestDto dto);
}
