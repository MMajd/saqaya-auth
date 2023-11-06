package com.saqaya.application.domain.service;

import com.saqaya.application.domain.model.User;
import com.saqaya.application.domain.value.RetrieveUserQuery;
import com.saqaya.application.port.in.RetrieveUserUseCase;
import com.saqaya.application.port.out.LoadUserPort;
import com.saqaya.common.UseCase;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RetrieveUserService implements RetrieveUserUseCase {
    private final LoadUserPort loadUserPort;

    @Override
    public User retrieveUser(RetrieveUserQuery query) {
        return loadUserPort.loadUser(query);
    }
}
