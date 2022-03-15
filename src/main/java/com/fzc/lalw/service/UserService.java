package com.fzc.lalw.service;

import com.fzc.lalw.domain.User;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */

public interface UserService {
    Boolean add(User user);
    User get(Integer id);
    User get(String email);
    User[] get(Integer index, Integer length);
    Boolean modify(User modifiedUser);
    Integer delete(Integer[] ids);

    Integer getTotalNum();
}
