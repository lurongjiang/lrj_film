package com.lrj.film.service.user;

import com.lrj.film.controller.user.vo.EnrollUserVo;
import com.lrj.film.controller.user.vo.UserInfoVo;
import com.lrj.film.service.common.exception.CommonServiceException;

/**
 * @author lrj
 */
public interface FilmUserServiceApi {
    /**
     * /**
     * 新增用户
     *
     * @param userVo 用户vo
     * @throws CommonServiceException 普通的异常
     */
    void userEnroll(EnrollUserVo userVo) throws CommonServiceException;

    /**
     * 检查用户名是否重名
     *
     * @param userName 用户名
     * @return 是否重名
     * @throws CommonServiceException 普通的异常
     */
    boolean checkUserName(String userName) throws CommonServiceException;

    /**
     * 用户认证
     *
     * @param userName 用户名
     * @param password 密码
     * @return 是否合法认证
     * @throws CommonServiceException 普通的异常
     */
    boolean userAuth(String userName, String password) throws CommonServiceException;

    /**
     * 获取用户信息
     *
     * @param userId 用户Id
     * @return 用户信息
     * @throws CommonServiceException 普通的异常
     */
    UserInfoVo getUserInfoVo(Integer userId) throws CommonServiceException;

    /**
     * 修改用户信息
     *
     * @param userInfoVo 用户信息
     * @return 用户信息
     * @throws CommonServiceException 普通的异常
     */
    UserInfoVo updateUserInfoVo(UserInfoVo userInfoVo) throws CommonServiceException;
}
