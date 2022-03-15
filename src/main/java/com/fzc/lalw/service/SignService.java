package com.fzc.lalw.service;

import com.fzc.lalw.domain.SignAble;
import com.fzc.lalw.domain.User;

import java.util.Map;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */

public interface SignService {
    SignAble login(Map<String, String> loginInfo);
    Boolean register(User actor);
    String createCode(String email);
}
