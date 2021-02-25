package com.skt.doss.common.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorVo {
	
    public final static String NONE = "0000";

    private String errorCode;
    private String errorMessage;

    public ErrorVo() {
        this(NONE, "");
    }
}
