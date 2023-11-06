package com.saqaya.adapter.out.persistence.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saqaya.adapter.out.persistence.entity.UserJpaEntity;
import com.saqaya.common.PersistenceService;

@PersistenceService
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, String> {
}
