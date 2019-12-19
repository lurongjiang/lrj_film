package com.lrj.film;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lrj.film.dao.entity.User;
import com.lrj.film.dao.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest(classes = FilmApplication.class)
class FilmApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        User zhangsan = new User(0, "zhangsan", "123");
        userMapper.insert(zhangsan);
        User lisi = new User(0, "lisi", "123");
        userMapper.insert(lisi);
        userMapper.updateById(new User(1, "wangwu", "123"));
        //条件where
        AbstractWrapper abstractWrapper = new UpdateWrapper();
        abstractWrapper.eq("password", "123");
        //目标值
        User user = new User();
        user.setPassword("aaaaaaaaaaaa");
        userMapper.update(user, abstractWrapper);
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
        userMapper.deleteById(8);
        AbstractWrapper delete=new QueryWrapper();
        delete.eq("username","lisi");
        userMapper.delete(delete);
        userMapper.selectList(null).forEach(System.out::println);
    }

}
