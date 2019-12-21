package com.lrj.film.service.user.impl;

import com.lrj.film.FilmApplication;
import com.lrj.film.controller.user.vo.EnrollUserVo;
import com.lrj.film.controller.user.vo.UserInfoVo;
import com.lrj.film.service.user.FilmUserServiceApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;

/**
 * @author lrj
 */
@SpringBootTest(classes = FilmApplication.class)
@ExtendWith(SpringExtension.class)
class FilmUserServiceImplTest {
    @Resource
    private FilmUserServiceApi userServiceApi;

    @Test
    void userEnroll() {
        EnrollUserVo userVo = new EnrollUserVo();
        userVo.setUsername("zhangsan");
        userVo.setPassword("1111");
        userVo.setPhone("12345678947");
        userVo.setEmail("111@qq.com");
        userServiceApi.userEnroll(userVo);
    }

    @Test
    void checkUserName() {
    }

    @Test
    void userAuth() {
    }

    @Test
    void getUserInfoVo() {
        UserInfoVo userInfoVo = userServiceApi.getUserInfoVo(2);
        System.out.println(userInfoVo);
    }

    @Test
    void updateUserInfoVo() {
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUuid(8);
        userInfoVo.setNickname("张三");
        userInfoVo.setLifeState("1ddd");
        UserInfoVo infoVo = userServiceApi.updateUserInfoVo(userInfoVo);
        System.out.println(infoVo);
    }
}