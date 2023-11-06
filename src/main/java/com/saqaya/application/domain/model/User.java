package com.saqaya.application.domain.model;

import java.util.Optional;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class User {
    private UserId id;

    @Getter
    private final String email;

    @Getter
    private final String firstName;

    @Getter
    private final String lastName;

    @Getter
    private final Boolean marketingConsent;

    @Value
    public static class UserId {
        private final String value;
    }

    public Optional<UserId> getId() {
        return Optional.ofNullable(this.id);
    }
}
