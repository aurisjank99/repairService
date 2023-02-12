package com.service.repairs.exception;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class BaseException extends RuntimeException {
    @NonNull
    private final ErrorCode code;

    public BaseException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(ErrorCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public BaseException(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
