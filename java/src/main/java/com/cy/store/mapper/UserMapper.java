package com.cy.store.mapper;


import com.cy.store.entity.User;

import java.util.Date;

public interface UserMapper {
    /**
     * 插入用户数据
     * @param user 用户数据
     * @return 受影响行数
     */
    Integer insert (User user );
    //根据用户名查找用户
    User findByUsername (String username );

    Integer updatePasswordByUid(Integer uid, String password, String modifiedUser, Date modifiedTime);

    User findBbyUid(Integer uid);

}
