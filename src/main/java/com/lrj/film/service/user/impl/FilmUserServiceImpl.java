package com.lrj.film.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lrj.film.common.Md5Util;
import com.lrj.film.controller.user.vo.EnrollUserVo;
import com.lrj.film.controller.user.vo.UserInfoVo;
import com.lrj.film.dao.entity.FilmUser;
import com.lrj.film.dao.mapper.FilmUserMapper;
import com.lrj.film.service.common.exception.CommonServiceException;
import com.lrj.film.service.user.FilmUserServiceApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import static com.lrj.film.common.StringUtil.isNumber;

/**
 * @author lrj
 */
@Service
public class FilmUserServiceImpl implements FilmUserServiceApi {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private FilmUserMapper filmUserMapper;

    @Override
    public void userEnroll(EnrollUserVo userVo) throws CommonServiceException {
        Optional.ofNullable(userVo).orElseThrow(() -> new CommonServiceException(500, "用户信息不能为空"));
        FilmUser user = new FilmUser();
        BeanUtils.copyProperties(userVo, user);
        user.setUserName(userVo.getUsername());
        Optional.ofNullable(userVo.getPassword()).orElseThrow(() -> new CommonServiceException(500, "密码不能为空"));
        user.setUserPassword(Md5Util.encrypt(userVo.getPassword()));
        int row = filmUserMapper.insert(user);
        logger.info("insert into film user ,effect row:{}", row);
        if (row < 1) {
            throw new CommonServiceException(502, "添加用户失败");
        }
    }

    @Override
    public boolean checkUserName(String userName) throws CommonServiceException {
        Optional.ofNullable(userName).orElseThrow(() -> new CommonServiceException(500, "用户名不能为空"));
        QueryWrapper<FilmUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        int count = filmUserMapper.selectCount(queryWrapper);
        return count > 0;
    }

    @Override
    public boolean userAuth(String userName, String password) throws CommonServiceException {
        Optional.ofNullable(userName).orElseThrow(() -> new CommonServiceException(500, "用户名不能为空"));
        Optional.ofNullable(password).orElseThrow(() -> new CommonServiceException(500, "密码不能为空"));
        //用户名是否存在
        QueryWrapper<FilmUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        FilmUser user = filmUserMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new CommonServiceException(500, "用户名不存在");
        }
        //密码是否匹配
        String encrypt = Md5Util.encrypt(password);
        logger.info("encrypt:{},db:{}", encrypt, user.getUserPassword());
        return user.getUserPassword().equals(encrypt);
    }

    @Override
    public UserInfoVo getUserInfoVo(Integer userId) throws CommonServiceException {
        FilmUser user = filmUserMapper.selectById(userId);
        if (user != null) {
            return entity2UserInfoVo(user);
        }
        throw new CommonServiceException(404, "找不到编号为" + userId + "的用户信息");
    }

    @Override
    public UserInfoVo updateUserInfoVo(UserInfoVo userInfoVo) throws CommonServiceException {
        Optional.ofNullable(userInfoVo).orElseThrow(() -> new CommonServiceException(500, "用户信息不能为空"));
        FilmUser filmUser = userInfoVo2FilmUser(userInfoVo);
        if (filmUser.getUuid() == null) {
            throw new CommonServiceException(500, "用户编号不能为空");
        }
        int row = filmUserMapper.updateById(filmUser);
        logger.info("update film user,effect row:{}", row);
        if (row > 0) {
            return getUserInfoVo(filmUser.getUuid());
        } else {
            throw new CommonServiceException(500, "修改用户信息失败");
        }
    }

    private UserInfoVo entity2UserInfoVo(FilmUser user) {
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setAddress(user.getAddress());
        userInfoVo.setNickname(user.getNickName());
        userInfoVo.setUsername(user.getUserName());
        userInfoVo.setBeginTime(user.getBeginTime().toEpochSecond(ZoneOffset.of("+8")));
        userInfoVo.setUpdateTime(user.getUpdateTime().toEpochSecond(ZoneOffset.of("+8")));
        userInfoVo.setLifeState(user.getLifeState() + "");
        BeanUtils.copyProperties(user, userInfoVo);
        return userInfoVo;
    }

    private FilmUser userInfoVo2FilmUser(UserInfoVo userInfoVo) {
        FilmUser filmUser = new FilmUser();
        filmUser.setAddress(userInfoVo.getAddress());
        filmUser.setNickName(userInfoVo.getNickname());
        filmUser.setUserName(userInfoVo.getUsername());
        filmUser.setUpdateTime(LocalDateTime.now());
        String lifeState = userInfoVo.getLifeState();
        if (isNumber(lifeState)) {
            filmUser.setLifeState(Integer.parseInt(lifeState));
        }
        BeanUtils.copyProperties(userInfoVo, filmUser);
        return filmUser;
    }
}
