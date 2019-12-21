package com.lrj.film.service.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 事务异常
 *
 * @author lrj
 */
@Getter
@Setter
public class DataTransactionException extends Exception {
    /**
     * 编码
     */
    private Integer code;
    /**
     * 描述信息
     */
    private String message;

    public DataTransactionException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
