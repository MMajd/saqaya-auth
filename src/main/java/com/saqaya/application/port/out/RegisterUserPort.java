package com.saqaya.application.port.out;

import com.saqaya.application.domain.model.User;

public interface RegisterUserPort {
    User registerUser(User user);
}