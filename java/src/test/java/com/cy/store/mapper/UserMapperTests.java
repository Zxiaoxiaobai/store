package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private  UserMapper userMapper;
    @Test
    public void insert(){
        User user =new User();
        user.setUsername("bca");
        user.setPassword("123456");
        Integer rows =userMapper.insert(user);
        System.out.println(rows);

    }
    @Test
    public  void findByUsername(){
        User user =userMapper.findByUsername("bca");
        System.out.println(user);
    }
}
