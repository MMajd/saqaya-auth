package com.saqaya.application.port.out;

import com.saqaya.application.domain.model.User;
import com.saqaya.application.domain.value.RetrieveUserQuery;

public interface LoadUserPort {
    User loadUser(RetrieveUserQuery query);
}
