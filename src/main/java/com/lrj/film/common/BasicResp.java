package com.lrj.film.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lrj
 */
@Getter
@Setter
@ApiModel(value = "返回实体类")
public class BasicResp {
    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码")
    private Integer status;
    /**
     * 提示信息
     */
    @ApiModelProperty(value = "提示信息")
    private String message;
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private Integer pageNo;
    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;
    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Integer totalPages;
    /**
     * 总计录数
     */
    @ApiModelProperty(value = "总计录数")
    private Long totals;
    /**
     * 数据
     */
    @ApiModelProperty(value = "数据")
    private Object data;


    private BasicResp() {
    }

    public static BasicResp success() {
        BasicResp basicResp = new BasicResp();
        basicResp.setStatus(0);
        return basicResp;
    }

    public static BasicResp fail(String message) {
        BasicResp basicResp = new BasicResp();
        basicResp.setStatus(1);
        basicResp.setMessage(message);
        return basicResp;
    }

    public static BasicResp resp(Integer status, String message) {
        BasicResp basicResp = new BasicResp();
        basicResp.setStatus(status);
        basicResp.setMessage(message);
        return basicResp;
    }

    public static BasicResp resp(Object data) {
        BasicResp basicResp = new BasicResp();
        basicResp.setStatus(0);
        basicResp.setData(data);
        return basicResp;
    }

    public static BasicResp resp(String message, Object data) {
        BasicResp basicResp = new BasicResp();
        basicResp.setStatus(0);
        basicResp.setMessage(message);
        basicResp.setData(data);
        return basicResp;
    }
}
