package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UsernameDuplicatedException;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    @RequestMapping("reg")

    public JsonResult<Void> reg(User user){
        /**JsonResult<Void> result =new JsonResult<>();

        try {
            userService.reg(user);
            result.setState(200);
            result.setMessage("小朱提醒你:恭喜注册成功");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
            result.setMessage("小朱提醒你：这个用户名被占用");

        }catch (InsertException e) {
            result.setState(5000);
            result.setMessage("小朱提醒你：产生了未知异常");

        }
        return result;
    }*/

        userService.reg(user);
        return new JsonResult<>(Ok);
    }
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User data = userService.login(username,password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        System.out.println(getUsernameFromSession(session));
        return new JsonResult<User>(Ok,data);
    }
}
