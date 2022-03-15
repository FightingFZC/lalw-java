package com.fzc.lalw.service;

import com.fzc.lalw.domain.Admin;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */

public interface AdminService {
    Boolean modify(Admin modifiedAdmin);
    Admin get(Integer id);
    Admin get(String account);
}
