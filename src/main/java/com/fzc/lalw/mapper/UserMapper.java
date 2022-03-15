package com.fzc.lalw.mapper;

import com.fzc.lalw.domain.User;

/**
* @author PerfectFu
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-03-11 18:50:51
* @Entity com.fzc.lalw.domain.User
*/
public interface UserMapper {
    Integer addUser(User user);
    Integer isExist(String email);
    String getPasswordByEmail(String email);
    String getPasswordById(Integer id);
    User getUserById(Integer id);
    User getUserByEmail(String email);
    User[] getUser(Integer index, Integer length);
    Integer modifyUser(User newUser);
    Integer deleteUser(Integer[] ids);

    Integer getTotalNum();
}
