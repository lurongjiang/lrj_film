package com.lrj.film.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lrj
 */
@RestController
@RequestMapping("example")
@Api("测试Swagger2 API样例")
public class ExampleController {
    @RequestMapping("test")
    @ApiOperation(value = "测试SwaggerValue", notes = "测试样例Swagger Note")
    @ApiImplicitParam(name = "str",
            paramType = "query",
            value = "入参str",
            required = true,
            dataType = "string")
    public String test(String str) {
        System.out.println(str);
        return "test=" + str;
    }
}
