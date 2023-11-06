package com.saqaya.application.domain.value;

import static com.saqaya.common.validation.Validation.validate;

public record RetrieveUserQuery(String id) {
    public RetrieveUserQuery(final String id) {
        this.id = id;
        validate(this);
    }
}