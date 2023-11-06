package com.saqaya.adapter.out.persistence;

import com.saqaya.adapter.out.persistence.entity.UserJpaEntity;
import com.saqaya.adapter.out.persistence.mapper.UserMapper;
import com.saqaya.adapter.out.persistence.repo.UserJpaRepository;
import com.saqaya.application.domain.model.User;
import com.saqaya.application.domain.value.RetrieveUserQuery;
import com.saqaya.application.port.out.LoadUserPort;
import com.saqaya.application.port.out.RegisterUserPort;
import com.saqaya.common.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserPersistenceAdapter
        implements LoadUserPort, RegisterUserPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User registerUser(User domain) {
        UserJpaEntity entity = userJpaRepository.save(
                userMapper.fromDomainToEntity(domain));

        return userMapper.fromEntityToDomain(entity);
    }

    @Override
    public User loadUser(RetrieveUserQuery query) {
        UserJpaEntity entity = userJpaRepository
                .findById(query.id())
                .orElseThrow(() -> new RuntimeException("Cannot find user with given id"));

        return userMapper.fromEntityToDomain(entity);
    }

}
