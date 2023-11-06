package com.saqaya.application.port.in;

import com.saqaya.application.domain.model.User;
import com.saqaya.application.domain.value.RetrieveUserQuery;

public interface RetrieveUserUseCase {
    User retrieveUser(RetrieveUserQuery query);
}
