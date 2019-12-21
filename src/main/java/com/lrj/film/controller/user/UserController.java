package com.lrj.film.controller.user;

import com.lrj.film.common.BasicResp;
import com.lrj.film.service.common.exception.CommonServiceException;
import com.lrj.film.service.user.FilmUserServiceApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lrj
 */
@RestController
@RequestMapping("user")
@Api(value = "用户模块")
public class UserController {
    @Resource
    private FilmUserServiceApi userServiceApi;

    @ApiOperation(value = "检查用户名是否被占用", notes = "Note是接口的详细描述")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", type = "String")
    })
    @PostMapping("checkUserName")
    public BasicResp checkUserName(String username) throws CommonServiceException {
        boolean existUserName = userServiceApi.checkUserName(username);
        if (existUserName) {
            return BasicResp.fail("用户名已存在");
        }
        return BasicResp.success();
    }
}
