package com.cy.store.controller;

import com.cy.store.service.ex.*;
import com.cy.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPBinding;


public class BaseController {
    //成功的状态码
    public static final  int Ok =200;
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void>handleException(Throwable e){
        JsonResult <Void> result =new JsonResult<>();
        if(e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("小朱提醒你：这个用户名被占用");
        }else if(e instanceof UserNotFoundException){
            result.setState(5001);
            result.setMessage("小朱提醒你：还没注册");
        }else if(e instanceof PasswordNotMatchException){
            result.setState(5002);
            result.setMessage("小朱提醒你：密码错误");
        }
        else if(e instanceof InsertException){
            result.setState(5000);
            result.setMessage("小朱提醒你：产生了未知异常");
        }
        return result;

    }
    public  final Integer getuidFromSession(HttpSession session){
        return  Integer.valueOf(session.getAttribute("uid").toString());
    }
    public  final String getUsernameFromSession(HttpSession session){
        return  session.getAttribute("username").toString();
    }
}
