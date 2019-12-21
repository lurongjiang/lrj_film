package com.lrj.film.service.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 普通的service异常,例如空判断等
 * @author lrj
 */
@Getter
@Setter
public class CommonServiceException extends RuntimeException {
    /**
     * 编码
     */
    private Integer code;
    /**
     * 描述信息
     */
    private String message;

    public CommonServiceException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
