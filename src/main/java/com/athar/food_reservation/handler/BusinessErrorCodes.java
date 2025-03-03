package com.athar.food_reservation.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.FORBIDDEN;

public enum BusinessErrorCodes {
    NO_CODE(0, NOT_IMPLEMENTED, "No Code"),
    INCORRECT_CURRENT_PASSWORD(300, BAD_REQUEST, "Current password is not correct."),
    NEW_PASSWORD_DOSE_NOT_MATCH(301, BAD_REQUEST, "Nwe password does not match"),
    ACCOUNT_LOCKED(302, FORBIDDEN, "User account is locked."),
    ACCOUNT_DISABLED(303, FORBIDDEN, "User account is disabled."),
    BAD_CREDENTIALS(304, FORBIDDEN, "Login and / or is incorrect.")
    ;

    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus httpStatus, String description ) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
