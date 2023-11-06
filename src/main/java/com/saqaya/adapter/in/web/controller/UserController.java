package com.saqaya.adapter.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saqaya.adapter.in.web.dto.request.GetUserRequestDto;
import com.saqaya.adapter.in.web.dto.request.RegisterUserRequestDto;
import com.saqaya.adapter.in.web.dto.response.GetUserResponseDto;
import com.saqaya.adapter.in.web.dto.response.GetUserWithEmailResponseDto;
import com.saqaya.adapter.in.web.dto.response.RegisterUserResponseDto;
import com.saqaya.adapter.in.web.service.GetUserWebService;
import com.saqaya.adapter.in.web.service.RegisterUserWebService;
import com.saqaya.common.WebAdapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final GetUserWebService getUserWebService;
    private final RegisterUserWebService registerUserWebServicee;

    @PostMapping
    ResponseEntity<RegisterUserResponseDto> registerUser(
            @RequestBody RegisterUserRequestDto registerUserRequestDto) {
        log.info("getUser RequestBody {}", registerUserRequestDto);
        return ResponseEntity
                .ok(registerUserWebServicee.register(registerUserRequestDto));
    }

    @GetMapping("/{id}")
    ResponseEntity<GetUserResponseDto> getUser(@PathVariable("id") String id) {
        GetUserRequestDto getUserRequestDto = new GetUserRequestDto(id);
        log.info("getUser RequestBody {}", getUserRequestDto);

        return ResponseEntity
                .ok(getUserWebService.get(getUserRequestDto));
    }
}
