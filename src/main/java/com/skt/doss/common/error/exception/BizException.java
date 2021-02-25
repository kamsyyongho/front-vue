package com.skt.doss.common.error.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;
    private String message;

    public BizException(String message) {
        this("8090", message);
    }

    public BizException(String code, String message) {
        this(code, null, message);
    }

    public BizException(String code, Throwable cause, String message) {
        super(cause!=null?cause.getMessage():code, cause);

        this.code = code;
        this.message = message;
    }
}
