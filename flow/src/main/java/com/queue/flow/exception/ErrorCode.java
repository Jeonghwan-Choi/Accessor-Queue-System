package com.queue.flow.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorCode {

    QUEUE_ALREADY_REGISTER_USER(HttpStatus.CONFLICT,"UQ-0001", "Already registered in queue");
    private final HttpStatus httpStatus;
    private final String code;
    private final String reason;

    public ApplicationException build() {
        return new ApplicationException(httpStatus, code, reason);
    }

    public ApplicationException build(Object ...arg) {
        return new ApplicationException(httpStatus, code, reason.formatted(arg));
    }
}
