package com.ssau.Hostel7.service.security;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    private static final int SALT_LENGTH = 8;

    private static final int HASH_LENGTH = 16;

    private static final int PARALLELISM = 4;

    private static final int MEMORY = 1024 * 256;

    private static final int ITERATIONS = 8;

    private PasswordEncoder encoder = new Argon2PasswordEncoder(
            SALT_LENGTH,
            HASH_LENGTH,
            PARALLELISM,
            MEMORY,
            ITERATIONS
    );

    @Override
    public String encode(CharSequence charSequence) {
        return encoder.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}
