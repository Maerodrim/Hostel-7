package com.ssau.Hostel7.helper.holders;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtConstantsHolder {

    @Value("${jwt.key}")
    private String jwtKey;

    @Value("${jwt.auth.expiration_time_ms}")
    private Long expirationTimeMs;

    @Value("${jwt.auth.cookie_name}")
    private String cookieName;

    @Value("${jwt.id_json_field_name}")
    private String idJsonFieldName;

}
