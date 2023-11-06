package com.saqaya.application.domain.service;

import com.saqaya.application.domain.model.User;
import com.saqaya.application.port.in.RegisterUserUseCase;
import com.saqaya.application.port.out.RegisterUserPort;
import com.saqaya.common.UseCase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterUserService implements RegisterUserUseCase {
    private final RegisterUserPort registerUserPort;

    @Override
    public User registerUser(RegisterUserCommand command) {
        return registerUserPort.registerUser(
                User.builder()
                        .firstName(command.firstName())
                        .lastName(command.lastName())
                        .email(command.email())
                        .marketingConsent(command.marketingConsent())
                        .build());
    }

}
