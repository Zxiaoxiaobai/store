package com.cy.store.service;

import com.cy.store.entity.User;

public interface IUserService {
    //注册
    void reg(User user);
    //登录
    User login(String username ,String password);
}
