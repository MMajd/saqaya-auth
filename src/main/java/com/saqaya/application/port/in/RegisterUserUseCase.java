package com.saqaya.application.port.in;

import static com.saqaya.common.validation.Validation.validate;

import com.saqaya.application.domain.model.User;

public interface RegisterUserUseCase {
    User registerUser(RegisterUserCommand command);

    public static record RegisterUserCommand(String firstName, String lastName, String email,
            boolean marketingConsent) {
        public RegisterUserCommand(final String firstName, final String lastName, final String email,
                final boolean marketingConsent) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.marketingConsent = marketingConsent;

            validate(this);
        }
    }
}
