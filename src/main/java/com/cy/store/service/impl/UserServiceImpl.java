package com.cy.store.service.impl;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.PasswordNotMatchException;
import com.cy.store.service.ex.UserNotFoundException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void reg(User user) {
        String username =user.getUsername();
        User result=userMapper.findByUsername(username);
        if(result!=null&&result.getIsDelete()!=1){
            throw new UsernameDuplicatedException("用户名被注册");
        }
        //加密密码
        String oldPassword =user.getPassword();
        String salt = UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        String md5password =md5Password(oldPassword ,salt);
        user.setPassword(md5password);
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date =new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);
        Integer rows =userMapper.insert(user);
        if(rows!=1){
            throw new InsertException("注册过程中出错了");
        }
    }

    @Override
    public User login(String username ,String password) {
        User result =userMapper.findByUsername(username);
        if(result==null){
            throw new UserNotFoundException("小朱提醒：还没注册");
        }
        //正确密码
        String rightPassword = result.getPassword();
        //输入密码
        String newPassword =md5Password(password, result.getSalt());
        //密码比较
        if(!rightPassword.equals(newPassword)){
            throw new PasswordNotMatchException("小朱提醒：密码错误");
        }
        if(result.getIsDelete()==1){
            throw  new UserNotFoundException("小朱提醒：数据不存在");
        }
        User user =new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    private String md5Password(String Password, String salt){
        for (int i = 0; i <3 ; i++) {
            Password =DigestUtils.md5DigestAsHex((salt+Password+salt).getBytes(StandardCharsets.UTF_8)).toUpperCase();
        }
        return Password;
    }
}
