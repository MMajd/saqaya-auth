package com.saqaya.adapter.out.persistence.mapper.impl;

import com.saqaya.adapter.out.persistence.entity.UserJpaEntity;
import com.saqaya.adapter.out.persistence.mapper.UserMapper;
import com.saqaya.application.domain.model.User;
import com.saqaya.application.domain.model.User.UserId;
import com.saqaya.common.WebService;

@WebService
public class UserMapperImpl implements UserMapper {

    @Override
    public User fromEntityToDomain(UserJpaEntity entity) {
        return User.builder()
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .marketingConsent(entity.getMarketingConsent())
                .id(new UserId(entity.getId()))
                .build();
    }

    @Override
    public UserJpaEntity fromDomainToEntity(User domain) {
        String userId = domain.getId().map(UserId::getValue).orElse(null);

        return UserJpaEntity.builder()
                .email(domain.getEmail())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .marketingConsent(domain.getMarketingConsent())
                .id(userId)
                .build();

    }

}
