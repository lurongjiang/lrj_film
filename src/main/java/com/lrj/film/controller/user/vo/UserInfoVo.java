package com.lrj.film.controller.user.vo;

import lombok.Data;

/**
 * @author lrj
 */
@Data
public class UserInfoVo {
    private Integer uuid;
    private String username;
    private String nickname;
    private String email;
    private String phone;
    private Integer sex;
    private String birthday;
    private String lifeState;
    private String biography;
    private String address;
    private String headAddress;
    private Long beginTime;
    private Long updateTime;
}
