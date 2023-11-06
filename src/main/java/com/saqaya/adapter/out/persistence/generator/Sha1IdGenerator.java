package com.saqaya.adapter.out.persistence.generator;

import java.lang.reflect.Field;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Value;

public class Sha1IdGenerator implements IdentifierGenerator {
    @Value("sha1.salt")
    private String sha1Salt;

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        try {
            Field emailField = object.getClass().getDeclaredField("email");
            emailField.setAccessible(true);
            Object emailValue = emailField.get(object);
            if (!(emailValue instanceof String)) {
                throw new RuntimeException("No email field on given object or field is not of String type");
            }

            String email = (String) emailValue;
            emailField.setAccessible(false);
            return DigestUtils.sha1Hex(email + sha1Salt);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }
}
