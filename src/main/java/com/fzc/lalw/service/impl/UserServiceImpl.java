package com.fzc.lalw.service.impl;

import com.fzc.lalw.domain.User;
import com.fzc.lalw.mapper.UserMapper;
import com.fzc.lalw.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public Boolean add(User user) {
        int num = userMapper.addUser(user);
        return num != 0;
    }

    @Override
    public User get(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User get(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public User[] get(Integer index, Integer length) {
        return userMapper.getUser(index, length);
    }

    @Override
    public Boolean modify(User modifiedUser) {
        int num = userMapper.modifyUser(modifiedUser);
        return num != 0;
    }

    @Override
    public Integer delete(Integer[] ids) {
        return userMapper.deleteUser(ids);
    }

    @Override
    public Integer getTotalNum() {
        return userMapper.getTotalNum();
    }
}
