package com.saqaya.adapter.out.persistence.mapper;

import com.saqaya.adapter.out.persistence.entity.UserJpaEntity;
import com.saqaya.application.domain.model.User;

public interface UserMapper {
    User fromEntityToDomain(UserJpaEntity entity);

    UserJpaEntity fromDomainToEntity(User domain);
}
