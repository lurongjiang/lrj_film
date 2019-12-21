package com.lrj.film.controller.user.vo;

import lombok.Data;

/**
 * @author lrj
 */
@Data
public class EnrollUserVo {
    private String username;
    private String password;
    private String email;
    private String phone;
    private String address;
}
