package com.ssau.Hostel7.helper.holders;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ErrorMessagesHolder {

    @Value("${entity.not_found}")
    private String entityNotFound;

}
