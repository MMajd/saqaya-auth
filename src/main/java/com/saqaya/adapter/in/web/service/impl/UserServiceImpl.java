package com.saqaya.adapter.in.web.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.saqaya.adapter.in.web.dto.GetUserRequestDto;
import com.saqaya.adapter.in.web.dto.GetUserResponseDto;
import com.saqaya.adapter.in.web.dto.RegisterUserRequestDto;
import com.saqaya.adapter.in.web.dto.RegisterUserResponseDto;
import com.saqaya.adapter.in.web.dto.GetUserWithEmailResponseDto;
import com.saqaya.adapter.in.web.dto.GetUserWithoutEmailResponseDto;
import com.saqaya.adapter.in.web.service.GetUserWebService;
import com.saqaya.adapter.in.web.service.JwtService;
import com.saqaya.adapter.in.web.service.RegisterUserWebService;
import com.saqaya.adapter.out.persistence.mapper.UserMapper;
import com.saqaya.adapter.out.persistence.repo.UserJpaRepository;
import com.saqaya.application.domain.model.User;
import com.saqaya.application.domain.value.RetrieveUserQuery;
import com.saqaya.application.port.in.RegisterUserUseCase;
import com.saqaya.application.port.in.RegisterUserUseCase.RegisterUserCommand;
import com.saqaya.application.port.in.RetrieveUserUseCase;
import com.saqaya.common.WebService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@WebService
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements
        RegisterUserWebService, GetUserWebService, UserDetailsService {
    private final RegisterUserUseCase registerUserUseCase;
    private final RetrieveUserUseCase retrieveUserUseCase;
    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Override
    public RegisterUserResponseDto register(RegisterUserRequestDto dto) {
        final User domainUser = registerUserUseCase
                .registerUser(
                        new RegisterUserCommand(dto.getFirstName(), dto.getLastName(),
                                dto.getEmail(),
                                dto.getMarketingConsent()));

        final String jwt = jwtService.generateToken(userMapper.fromDomainToEntity(domainUser));
        return RegisterUserResponseDto
                .builder()
                .id(domainUser.getId().map(id -> id.getValue()).orElse(null))
                .accessToken(jwt)
                .build();
    }

    @Override
    public GetUserResponseDto get(GetUserRequestDto dto) {
        User domainUser = retrieveUserUseCase.retrieveUser(
                new RetrieveUserQuery(dto.getId()));

        log.info("retrieved domain user: {}", domainUser);

        if (domainUser.getMarketingConsent()) {
            return GetUserWithEmailResponseDto.builder()
                    .firstName(domainUser.getFirstName())
                    .lastName(domainUser.getLastName())
                    .marketingConsent(domainUser.getMarketingConsent())
                    .email(domainUser.getEmail())
                    .build();
        }

        return GetUserWithoutEmailResponseDto.builder()
                .firstName(domainUser.getFirstName())
                .lastName(domainUser.getLastName())
                .marketingConsent(domainUser.getMarketingConsent())
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        return userJpaRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with %d not found", id)));
    }
}
