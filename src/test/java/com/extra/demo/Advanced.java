package com.extra.demo;

import com.extra.demo.mptest.entity.User;
import com.extra.demo.mptest.mapper.UserMapper;
import net.minidev.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author extralIl@1141517977
 * @date 2020/5/13 22:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Advanced {

    @Autowired
    UserMapper userMapper;

    @Test
    public void logicalDelete(){
        int rows = userMapper.deleteById(1260580860751572993L);
        System.out.println(rows);
    }

    @Test
    public void selectTestDeleted(){
        //注意,自定义查询语句不会自动拼接deleted = 0
        User user = userMapper.selectById(1260580860751572993L);
        if(user!=null){
            System.out.println(user.toString());
        }
    }

    @Test
    public void autoInsertOrUpdate(){
        User user = new User();
        user.setName("jiweihao");
        user.setAge(12);
        user.setEmail("asdfasf");
        int rows = userMapper.insert(user);

        System.out.println(rows);
    }

}
