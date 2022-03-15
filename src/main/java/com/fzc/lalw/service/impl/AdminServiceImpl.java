package com.fzc.lalw.service.impl;

import com.fzc.lalw.domain.Admin;
import com.fzc.lalw.mapper.AdminMapper;
import com.fzc.lalw.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author PerfectFu
 * @projectName IntelliJ IDEA
 * @date 2022/3/12
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminMapper adminMapper;
    @Override
    public Boolean modify(Admin modifiedAdmin) {
        Admin oldAdmin = adminMapper.getAdminById(modifiedAdmin.getId());
        if (!oldAdmin.getAccount().equals(modifiedAdmin.getAccount())) {
            if (isExists(modifiedAdmin)) {
                return false;
            }
        }
        int num = adminMapper.modifyAdmin(modifiedAdmin);
        return num != 0;
    }

    @Override
    public Admin get(Integer id) {
        return adminMapper.getAdminById(id);
    }

    @Override
    public Admin get(String account) {
        return adminMapper.getAdminByAccount(account);
    }

    public Boolean isExists(Admin admin) {
        Admin checkAdmin =
                adminMapper.getAdminByAccount(admin.getAccount());
        return checkAdmin != null;
    }
}
