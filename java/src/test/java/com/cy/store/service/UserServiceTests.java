package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserService userservice;
    @Test
    public void reg(){
        try {
            User user =new User();
            user.setUsername("xiaoxiao");
            user.setPassword("45246");
            userservice.reg(user);
            System.out.println("ok");
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass().getSimpleName());
        }

    }
    @Test
    public void login(){

        System.out.println(userservice.login("test01","123456"));
    }

}
